package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.MemberService;
import com.example.vo.MemberVO;

/**
 * 회원 Controller
 *
 * @GetMapping  : 화면 조회 (GET)
 * @PostMapping : 등록/수정 처리 (POST)
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 등록 폼 화면
     * GET /member/insertForm.do
     */
    @GetMapping("/insertForm.do")
    public String insertForm() {
        return "member/insertForm";
    }

    /**
     * 회원 등록 (jQuery ajax)
     * POST /member/insert.do
     */
    @PostMapping(
            value = "/insert.do",
            consumes = "application/x-www-form-urlencoded",
            produces = "text/plain; charset=UTF-8"
    )
    @ResponseBody
    public String insert(MemberVO memberVO) {

        memberService.insertMember(memberVO);

        return "success";
    }

    /**
     * 수정 폼 화면
     * GET /member/updateForm.do
     */
    @GetMapping("/updateForm.do")
    public String updateForm() {
        return "member/updateForm";
    }

    /**
     * 회원 수정 (jQuery ajax)
     * POST /member/update.do
     */
    @PostMapping(
            value = "/update.do",
            consumes = "application/x-www-form-urlencoded",
            produces = "text/plain; charset=UTF-8"
    )
    @ResponseBody
    public String update(MemberVO memberVO) {

        memberService.updateMember(memberVO);

        return "success";
    }
}
