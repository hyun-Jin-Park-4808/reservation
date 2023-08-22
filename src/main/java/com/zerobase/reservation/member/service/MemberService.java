package com.zerobase.reservation.member.service;


import com.zerobase.reservation.member.dto.MemberDto;

public interface MemberService {

    MemberDto register(String userId, String userName, String phone, String password, boolean adminYN);
}
