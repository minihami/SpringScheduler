package jimin.scheduler.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("login")
    public String loginForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "member/login-form";
    }

    @PostMapping("login")
    public String login(@ModelAttribute Member member, HttpSession session, Model model) {
        System.out.println("member.getId() = " + member.getId());
        System.out.println("member.getPw() = " + member.getPw());

        Member findMember = memberService.login(member.getId(), member.getPw());
        if (findMember == null) {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다");
            return "member/login-form";
        }

        session.setAttribute("loginMember", findMember);
        return "redirect:/home";
    }
}
