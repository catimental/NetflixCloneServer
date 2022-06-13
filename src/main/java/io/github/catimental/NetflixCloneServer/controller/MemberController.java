package io.github.catimental.NetflixCloneServer.controller;

import io.github.catimental.NetflixCloneServer.domain.Member;
import io.github.catimental.NetflixCloneServer.dto.LoginMemberForm;
import io.github.catimental.NetflixCloneServer.dto.RegistrationMemberForm;
import io.github.catimental.NetflixCloneServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public String create(RegistrationMemberForm form) {
        var member = new Member();
        member.setLoginId(form.getLoginId());
        member.setLoginPassword(form.getLoginPassword());
        memberService.register(member);

        return "redirect:/";
    }

    @PostMapping("/members/register")
    public String register(RegistrationMemberForm form) {
        return create(form);
    }

    @PostMapping("/members/login")
    @ResponseBody

    public String login(LoginMemberForm form) {//todo refartoring
        final var optionalMember =  memberService.login(form.getLoginId(), form.getLoginPassword());
        if(optionalMember.isPresent()) {
            return String.valueOf(optionalMember.get().getId());
        }

        return "";
    }


    @GetMapping("/members")
    public String list(Model model) {
        var members = memberService.getMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
