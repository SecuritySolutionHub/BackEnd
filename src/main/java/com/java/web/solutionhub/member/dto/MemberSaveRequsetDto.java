package com.java.web.solutionhub.member.dto;


import java.time.LocalDateTime;

import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.domain.enum_package.MemberStatic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequsetDto {
    private String userId;
    private String password;
    private String companyEmail;
    private MemberStatic.memberRoll memberRoll;

    @Builder
    MemberSaveRequsetDto(String userId, String password, String companyEmail, MemberStatic.memberRoll roll){
        this.userId = userId;
        this.password = password;
        this.companyEmail = companyEmail;
        this.memberRoll = roll;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .password(password)
                .companyEmail(companyEmail)
                .memberRoll(memberRoll)
                .createDate(LocalDateTime.now())
                .build();
    }
}
