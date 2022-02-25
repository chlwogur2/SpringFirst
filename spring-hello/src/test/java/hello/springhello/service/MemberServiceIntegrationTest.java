package hello.springhello.service;


import hello.springhello.domain.Member;
import hello.springhello.repository.MemberRepository;
import hello.springhello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long memberID =  memberService.join(member);

        //then
        Member memberResult = memberService.findOne(memberID).get();
        assertThat(member.getName()).isEqualTo(memberResult.getName());
    }

    @Test
    void 중복_회원_검증(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

//        //when
//        memberService.join(member1);
//        try{
//            memberService.join(member2);
//            fail();     // 이름을 똑같이 설정했는데도 예외가 안터지면 실패한거
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
//        이렇게 테스트코드에서 try-catch문 쓰는 것을 별로 안 좋아함

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));

    }




    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
