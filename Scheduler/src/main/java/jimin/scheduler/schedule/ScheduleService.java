package jimin.scheduler.schedule;

import java.util.List;

public interface ScheduleService {

    Schedule register(Schedule schedule);
    Schedule findOne(Long scheduleId);
    List<Schedule> findByMember(Long memberSuid);
    void update(Schedule schedule);
    void delete(Long scheduleId);
}
