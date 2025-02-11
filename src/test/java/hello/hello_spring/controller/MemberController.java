package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller를 통해 spring container에 넣어두고 관리해줌 -> spring bean이 관리됨
@Controller
public class MemberController {

    private MemberService memberService;

    //생성사 주입방법
    //스프링컨테이너의 MemberService를 자동으로 넣어줌(연결시켜줌)->Dependency Injection(의존관계 주입)-
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
