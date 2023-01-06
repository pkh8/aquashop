package com.shop.aqua.config;






import com.shop.aqua.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity // CSRF 보호 기능이 활성화, Spring Security 설정 활성화
public class SecurityConfig {


	@Autowired
	MemberService memberService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //http 요청에 대한 보안을 설정.
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


		http.formLogin()
				.loginPage("/members/login") // 로그인 페이지 URL 생성
				.defaultSuccessUrl("/") // 로그인 시 이동할 URL 설정
				.usernameParameter("email") // 로그인 시 사용할 파라미터 이름으로 email 설정
				.failureUrl("/members/login/error")// 로그인 실패 시 이동할 URL 설정
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃 URL 설정
				.logoutSuccessUrl("/")
		;

		http.authorizeHttpRequests()
				.mvcMatchers("/**", "/members/**", "/item/**").permitAll()  // 모든 사용자가 접근 가능
				.mvcMatchers("/admin/**").hasRole("ADMIN") //ADMIN 접근 기능   toString, .name도 가능
				.anyRequest().authenticated() //설정해준 경로 외 나머지 경로는 모두 인증을 요구.
		;

		http.exceptionHandling()
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())

		;
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


	@Bean	//static 디렉터리의 하위 파일 목록은 인증 무시(= 항상 통과)
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/assets/**", "/images/**", "/js/**", "/css/**");

	}



}
