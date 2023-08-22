package com.zerobase.reservation.member.controller;

import com.zerobase.reservation.member.dto.RegisterDto;
import com.zerobase.reservation.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/register") // 회원 가입
    public RegisterDto.Response register(@RequestBody RegisterDto.Request request) {
        return RegisterDto.Response.from(
                memberService.register(
                        request.getUserId(),
                        request.getUserName(),
                        request.getPhone(),
                        request.getPassword(),
                        request.isAdminYN()
                )
        );
    }
}
