package jimin.scheduler;

import jakarta.servlet.http.HttpSession;
import jimin.scheduler.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("home")
    public String home(HttpSession session, Model model) {
        Member loginMember = (Member)session.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", loginMember);
        return "home";
    }
}
