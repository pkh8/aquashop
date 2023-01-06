package com.shop.aqua.entity;


import com.shop.aqua.constant.Role;
import com.shop.aqua.dto.MemberFormDto;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Entity
@Table(name="member")
@Data
public class Member extends BaseEntity{
	
	
	
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 	// 회원 번호

	private String memberName;	// 회원 이름
	
	private String username;	// 아이디
	
	@Column(unique = true)
	private String email; 
	

	private String memberAddress;
	private String zipCode;
	private String addressDetail;
	private String extraAddress;
	
	private String Phone;
	
	private String memberPw;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	//member테이블에 회원생성
    public static  Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
    	member.setMemberName(memberFormDto.getMemberName());
        member.setUsername(memberFormDto.getUsername());
        member.setEmail(memberFormDto.getEmail());
        member.setZipCode(memberFormDto.getZipCode());
        member.setMemberAddress(memberFormDto.getMemberAddress());
   		member.setAddressDetail(memberFormDto.getAddressDetail());
		member.setExtraAddress(memberFormDto.getExtraAddress());
        member.setPhone(memberFormDto.getPhone());
		String password = passwordEncoder.encode(memberFormDto.getMemberPw());
		member.setMemberPw(password);
//		String password2 = passwordEncoder.encode(memberFormDto.getMemberPw2());
//		member.setMemberPw(password2);
        member.setRole(Role.USER);	// 상품 등록 관리는 ADMIN만 가능하다.
        return member;
    }




	public void updateMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		//Member 엔티티를 수정하는 메소드
		this.memberName = memberFormDto.getMemberName();
		this.email = memberFormDto.getEmail();
		this.memberPw = passwordEncoder.encode(memberFormDto.getMemberPw());
//		this.memberPw = memberFormDto.getMemberPw();
		this.Phone = memberFormDto.getPhone();
		this.zipCode = memberFormDto.getZipCode();
		this.memberAddress = memberFormDto.getMemberAddress();
		this.role = memberFormDto.getRole();
	}



}

