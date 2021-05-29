package com.java.web.solutionhub.member.dto;


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
    private MemberStatic.memberRoll bAdmin;

    @Builder
    pubilc MemberSaveRequsetDto(String userId, String password, String companyEmail, MemberStatic.memberRoll bAdmin){
        this.userId = userId;
        this.password = password;
        this.companyEmail = companyEmail;
        this.bAdmin = bAdmin;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .password(password)
                .companyEmail(companyEmail)
                .bAdmin(bAdmin)
                .build();
    }
}
