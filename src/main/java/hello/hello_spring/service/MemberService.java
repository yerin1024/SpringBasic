package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //스프링컨테이너에 Service로 등록해줌
public class MemberService {

    private final MemberRepository memberRepository;

    //MemberService입장에서는 MemberRepository를 직접 선언하지않고 외부에서 넣어줌 -> Dpendency Ingection
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //ifPresent는 optional로 받아서 가능 null일 가능성 있으면 optional로 받으면 편함
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { //ifPresent는 optional로 받아서 가능 null일 가능성 있으면 optional로 받으면 편함
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
