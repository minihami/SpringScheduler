package jimin.scheduler.schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Schedule save(Schedule schedule);
    Optional<Schedule> findById(Long scheduleId);
    List<Schedule> findByMemberSuid(Long memberSuid);
    void update(Schedule schedule);
    void delete(Long scheduleId);

}
