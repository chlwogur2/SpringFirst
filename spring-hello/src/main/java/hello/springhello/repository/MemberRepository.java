package hello.springhello.repository;

import hello.springhello.domain.Member;

import java.util.List;
import java.util.Optional;


// 회원을 저장하는 역할은 MemberRepository 지만,
// 구현을 메모리에 할 거야,
// or DB랑 연동해서 JDBC랑 할거냐의 차이
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
