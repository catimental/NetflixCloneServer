package io.github.catimental.NetflixCloneServer.controller;

import io.github.catimental.NetflixCloneServer.domain.Member;
import io.github.catimental.NetflixCloneServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        var member = new Member();
        member.setLoginId(form.getLoginId());
        member.setLoginPassword(form.getLoginPassword());
        memberService.register(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        var members = memberService.getMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
