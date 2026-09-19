package jimin.scheduler.schedule;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MemoryScheduleRepositoryImpl implements ScheduleRepository{

    public static final Map<Long, Schedule> store = new ConcurrentHashMap<>();
    private static final AtomicLong seq = new AtomicLong();

    @Override
    public Schedule save(Schedule schedule) {
        schedule.setScheduleId(seq.incrementAndGet());
        store.put(schedule.getScheduleId(), schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> findById(Long scheduleId) {
        return Optional.ofNullable(store.get(scheduleId));
    }

    @Override
    public List<Schedule> findByMemberSuid(Long memberSuid) {
        return store.values().stream()
                .filter(schedule -> schedule.getMemberSuid().equals(memberSuid))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Schedule schedule) {
        store.put(schedule.getScheduleId(), schedule);
    }

    @Override
    public void delete(Long scheduleId) {
        store.remove(scheduleId);
    }
}
