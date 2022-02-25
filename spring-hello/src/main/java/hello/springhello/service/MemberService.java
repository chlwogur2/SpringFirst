package hello.springhello.service;

import hello.springhello.domain.Member;
import hello.springhello.repository.MemberRepository;
import hello.springhello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join(Member member){
        // 같은 이름인 회원이 있으면 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m-> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); ----------------- 이렇게 적으면 optional 이 반환값이 됨

        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
