package jimin.scheduler.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Schedule {

    @Getter @Setter
    private Long ScheduleId;

    @Getter @Setter
    private Long memberSuid;

    @Getter @Setter
    private LocalDateTime startTime;

    @Getter @Setter
    private LocalDateTime endTime;

    @Getter @Setter
    private String detail;
}
