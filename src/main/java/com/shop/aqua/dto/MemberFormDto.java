package com.shop.aqua.dto;

import com.shop.aqua.constant.Role;
import com.shop.aqua.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberFormDto {
	
	
	private Long id;	//회원 번호
	

	@NotBlank(message = " 이름은 필수 입력 값입니다.")
	private String memberName; // 회원 이름
	
	@NotBlank(message ="아이디는 필수입력값입니다.")
	private String username;	//회원아이디
  
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;	

	
	@NotEmpty(message = " 비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
      message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String memberPw;

//	@NotEmpty(message = " 비밀번호는 필수 입력 값입니다.")
//	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//			message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
//	private String memberPw2;


	@NotEmpty(message ="휴대전화 번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message="전화번호가 바르지 않습니다.")
	private String phone;	// 휴대폰번호

	@NotEmpty(message = "우편번호는 필수 입력 갑입니다.")
	private String zipCode;	// 우편번호
	
	@NotEmpty(message = "주소는 필수 입력 갑입니다.")
	private String memberAddress; // 주소
	
	@NotEmpty(message = "상세주소를 입력해주세요.")
	private String addressDetail;

	@NotEmpty(message = "참고주소를 입력해주세요.")
	private String extraAddress;

	private Role role;

	private List<MemberFormDto> memberFormDtoList = new ArrayList<>();

	private static ModelMapper modelMapper = new ModelMapper();

	public static MemberFormDto of(Member member){
		return modelMapper.map(member, MemberFormDto.class);
	}




}
