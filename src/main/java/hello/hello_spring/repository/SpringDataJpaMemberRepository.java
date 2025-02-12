package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 상속받음으로써 Spring jpa가 구현체를 자동으로 만들고 스프링빈에 등록해줌 ->spingConfig에서 injection받을 수 있음
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //interface는 다중 상속 가능

    //JQPL: select m from Member m where m.name=?
    @Override
    Optional<Member> findByName(String name); //얘는 spring jpa에 없음
}
