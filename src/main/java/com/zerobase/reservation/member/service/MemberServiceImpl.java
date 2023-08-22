package com.zerobase.reservation.member.service;

import com.zerobase.reservation.member.dto.MemberDto;
import com.zerobase.reservation.member.entity.Member;
import com.zerobase.reservation.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberDto register(String userId, String userName,
                              String phone, String password, boolean adminYN) {

        return MemberDto.fromEntity(
                memberRepository.save(Member.builder()
                        .userId(userId)
                        .userName(userName)
                        .phone(phone)
                        .password(password)
                        .regDt(LocalDateTime.now())
                        .adminYN(adminYN)
                        .build()
                )
        );
    }
}
