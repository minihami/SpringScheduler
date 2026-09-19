package jimin.scheduler.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryMemberRepositoryImpl implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    private static final AtomicLong seq = new AtomicLong(0);

    @Override
    public void save(Member member) {
        member.setSuid(seq.incrementAndGet());
        store.put(member.getSuid(), member);
    }

    @Override
    public Optional<Member> findById(String id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Member> findByIdAndPw(String id, String pw) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id) && member.getPw().equals(pw))
                .findFirst();
    }


    @Override
    public Optional<Member> findBySuid(Long suid) {
        return Optional.ofNullable(store.get(suid));
    }
}
