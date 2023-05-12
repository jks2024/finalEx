package com.kh.finalEx.controller;

import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/member")
public class MemberController {
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // POST : 회원 가입
    @PostMapping(value="/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        String id = regData.get("id");
        String pwd = regData.get("pwd");
        String name = regData.get("name");
        String mail = regData.get("mail");
        boolean result = memberService.regMember(id, pwd, name, mail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // POST : 로그인 체크
    @PostMapping(value="/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String user = loginData.get("user");
        String pwd = loginData.get("pwd");
        boolean result = memberService.loginCheck(user, pwd);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
    // GET : 회원 조회
    @GetMapping(value="/member")
    public ResponseEntity<List<MemberDto>> memberList(@RequestParam String userId) {
        log.info("USER ID : " + userId);
        List<MemberDto> list = null;
        if(userId.equals("ALL")) {
           list = memberService.getMemberList();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // POST : 회원 탈퇴
    @PostMapping(value="/del")
    public ResponseEntity<Boolean> memberDelete(@RequestBody Map<String, String> delData) {
        String id = delData.get("id");
        boolean result = memberService.delMember(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
