package jimin.scheduler.schedule;

import jakarta.servlet.http.HttpSession;
import jimin.scheduler.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 1) 스케줄 등록 폼 페이지
    @GetMapping("/new")
    public String createForm(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login-form";
        }
        model.addAttribute("schedule", new Schedule());
        return "schedule/schedule-form";
    }

    // 2) 스케줄 등록 처리
    @PostMapping("/new")
    public String create(@ModelAttribute Schedule schedule,
                         HttpSession session,
                         Model model) {

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login-form";
        }

        schedule.setMemberSuid(loginMember.getSuid());  // 현재 로그인한 회원 소유로 지정

        try {
            scheduleService.register(schedule);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("schedule", schedule);
            return "schedule/schedule-form";
        }

        return "redirect:/schedule/list";
    }

    // 3) 내 스케줄 목록
    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login-form";
        }

        List<Schedule> schedules = scheduleService.findByMember(loginMember.getSuid());
        model.addAttribute("schedules", schedules);
        return "schedule/schedule-list";
    }

    // 4) 스케줄 상세보기
    @GetMapping("/{scheduleId}")
    public String detail(@PathVariable Long scheduleId, Model model) {
        Schedule schedule = scheduleService.findOne(scheduleId);
        model.addAttribute("schedule", schedule);
        return "schedule/schedule-detail";
    }

    // 5) 스케줄 수정 폼
    @GetMapping("/{scheduleId}/edit")
    public String editForm(@PathVariable Long scheduleId, Model model) {
        Schedule schedule = scheduleService.findOne(scheduleId);
        model.addAttribute("schedule", schedule);
        return "schedule/schedule-edit";
    }

    // 6) 스케줄 수정 처리
    @PostMapping("/{scheduleId}/edit")
    public String edit(@PathVariable Long scheduleId,
                       @ModelAttribute Schedule schedule) {
        schedule.setScheduleId(scheduleId);  // URL의 id로 강제 고정 (변조 방지)
        scheduleService.update(schedule);
        return "redirect:/schedule/" + scheduleId;
    }

    // 7) 스케줄 삭제
    @PostMapping("/{scheduleId}/delete")
    public String delete(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return "redirect:/schedule/list";
    }
}