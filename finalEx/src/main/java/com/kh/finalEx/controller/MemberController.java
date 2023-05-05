package com.kh.finalEx.controller;

import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MemberController {
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // POST : 회원 가입
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        String id = regData.get("id");
        String pwd = regData.get("pwd");
        String name = regData.get("name");
        String mail = regData.get("mail");
        boolean result = memberService.regMember(id, pwd, name, mail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
