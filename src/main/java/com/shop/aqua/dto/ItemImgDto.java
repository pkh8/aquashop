package com.shop.aqua.dto;


import com.shop.aqua.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {
	
	
	private Long id;
	
	private String imgName;
	
	private String oriImgName;
	
	private String imgUrl;
	
	private String repImgYn;
	
	   private  static ModelMapper modelMapper = new ModelMapper();	

	   public static ItemImgDto of(ItemImg itemImg) {
		   return modelMapper.map(itemImg, ItemImgDto.class);
	        // ItemImg 엔티티 객체를 파라미터로 받아서 Productimg 객체의 자료형과 메버변수의 이름이 같을때 ItemImgDto로 값을 복사해서
	        // 반환한다. static 메소드로 선언해서 ItemImgDto 객체를 생성하지 않아도 호출이 가능하다.
	   
	   }
	   
	   
}
