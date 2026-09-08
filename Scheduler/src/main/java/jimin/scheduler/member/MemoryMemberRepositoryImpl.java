package jimin.scheduler.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepositoryImpl implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getSuid(), member);
    }

    @Override
    public Member findBySuid(Long suid) {
        return store.get(suid);
    }
}
