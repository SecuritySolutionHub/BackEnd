package com.java.web.solutionhub.member.domain;


import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {

	
	@NotEmpty(message = "USER ID cannot be an empty value.")
	String userId;
	
	@NotEmpty(message = "Password cannot be an empty value.")
	@Builder.Default
	String password = "";
	
	@NotEmpty(message = "Compatn email cannot be an empty value")
	String companyEmail;
	
	String memberRoll;
}
