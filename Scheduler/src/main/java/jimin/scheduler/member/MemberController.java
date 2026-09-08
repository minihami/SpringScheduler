package jimin.scheduler.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("signup")
    public String signupForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "member/signup-form";
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute Member member, Model model)
    {
        System.out.println("member.getId() = " + member.getId());
        memberService.join(member);

        model.addAttribute("member", member);
        return "member/signup-result";
    }
}
