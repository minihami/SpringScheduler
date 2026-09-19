package jimin.scheduler.member;

public interface MemberService {

    void join(Member member);
    Member login(String id, String pw);
    Member findMember(Long suid);
}
