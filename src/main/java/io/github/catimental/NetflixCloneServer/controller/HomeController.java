package io.github.catimental.NetflixCloneServer.controller;

import io.github.catimental.NetflixCloneServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
