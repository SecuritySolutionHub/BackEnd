package com.java.web.solutionhub.config.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.web.solutionhub.member.repository.JpaMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
	private final JpaMemberRepository jpaMemberRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) jpaMemberRepository.findByUserId(username)
				.orElseThrow(() -> new UsernameNotFoundException("Can't find user"));
	}

}
