package jimin.scheduler.member;

public interface MemberRepository {
    void save(Member member);
    Member findBySuid(Long suid);

}
