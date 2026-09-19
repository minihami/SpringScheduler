package jimin.scheduler.member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByIdAndPw(String id, String pw);
    Optional<Member> findBySuid(Long suid);

}
