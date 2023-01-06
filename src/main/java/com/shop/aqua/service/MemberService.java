package com.shop.aqua.service;

import com.shop.aqua.dto.MemberFormDto;
import com.shop.aqua.entity.Member;
import com.shop.aqua.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class

MemberService implements UserDetailsService{
//
//	 private final MemberRepository memberRepository;
//
//	// 회원가입
//	public Member saveMember(Member member) {
//		validateDuplicateMember(member);
//		return memberRepository.save(member);
//	}
//
//	// 중복 회원 검증
//	private void validateDuplicateMember(Member member) {
//		Member findMember = memberRepository.findByUsername(member.getUsername());
//		if(findMember != null) {
//			throw new IllegalStateException("이미 가입된 회원입니다.");
//		}
//
//	}
//
////	// 회원 전체 조회
////	public List<Member> findMembers(){
////		return memberRepository.findAll();
////	}
////	public Member findOne(Long memberId) {
////		return memberRepository.findOne(id);
////	}
//
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//
//		Member member = memberRepository.findByUsername(username);
//
//		if(member == null) {
//			throw new UsernameNotFoundException(username);
//	}
//	return User.builder()
////			.username(member.getEmail()) // username email이 일치하면
//			.username(member.getUsername())
//			.password(member.getMemberPw())
//			.roles(member.getRole().toString())
//		    .build();
//
//
//}



	private final MemberRepository memberRepository;

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 회원가입
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}

	// 중복 회원 검증
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}

	}

	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public MemberFormDto getMemberDtl(Long memberId){
		Member member = memberRepository.findById(memberId).get();
		List<MemberFormDto> memberFormDtoList = new ArrayList<>();

		MemberFormDto memberFormDto = MemberFormDto.of(member);
		memberFormDtoList.add(memberFormDto);

		memberFormDto.setMemberFormDtoList(memberFormDtoList);

		return memberFormDto;
	}

	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public MemberFormDto getMypageList(String email){
		Member member = memberRepository.findByEmail(email);
		MemberFormDto memberFormDto = MemberFormDto.of(member);

		return memberFormDto;
	}

	public void deleteMember(Long id){
		memberRepository.deleteById(id);
	}

	public long updateMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) throws Exception{
		Member member = memberRepository.findById(memberFormDto.getId()).orElseThrow(EntityNotFoundException::new);
		member.updateMember(memberFormDto ,passwordEncoder);

		return member.getId();
	}





	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		Member member = memberRepository.findByEmail(email);

		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		return User.builder()
				.username(member.getEmail()) // username email이 일치하면
				.password(member.getMemberPw())
				.roles(member.getRole().toString())
				.build();


	}


}
