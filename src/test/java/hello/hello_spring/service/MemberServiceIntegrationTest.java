package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//MemberServiceTest 같이 순수자바 테스트하는 것을 단위테스트라하고
//MemberIntegrationTest 같이 스프링 띄우고 디비 연결해서 테스트하는 것을 통합테스트라 함
//단위단위로 테스트 하는 단위테스트가 좋은 테스트일 확률이 높음
@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행 -> 진짜로 스프링을 띄워서 테스트
@Transactional //테스트케이스에서 @Transactional 사용 -> 테스트시작 전에 트랜잭션 시작하고, 테스트 완료 후 롤백 -> 반복테스트 가능
class MemberServiceIntegrationTest {

    //원래는 생성자 주입방식을 추천하지만
    //test에서는 제일 편한 필드 주입방식을 사용(@Autowired)
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        //given: 어떤 상황이 주어졌는데
        Member member = new Member();
        member.setName("spring2");

        //when: 이거를 실행했을 때
        Long saveId = memberService.join(member);

        //then: 이 결과가 나와야 돼
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

//    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //member2를 넣은 join을 실행했을 때 IllegalStateException가 발생하는지를 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}