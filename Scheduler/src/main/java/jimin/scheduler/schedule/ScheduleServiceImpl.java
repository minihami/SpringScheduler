package jimin.scheduler.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    @Override
    public Schedule register(Schedule schedule) {
        if (schedule.getStartTime() != null && schedule.getEndTime() != null
        && schedule.getStartTime().isAfter(schedule.getEndTime())) {
            throw new IllegalArgumentException("StartTime must be before EndTime");
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findOne(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule is Not exist"));
    }

    @Override
    public List<Schedule> findByMember(Long memberSuid) {
        return scheduleRepository.findByMemberSuid(memberSuid);
    }

    @Override
    public void update(Schedule schedule) {
        scheduleRepository.update(schedule);
    }

    @Override
    public void delete(Long scheduleId) {
        scheduleRepository.delete(scheduleId);

    }
}
