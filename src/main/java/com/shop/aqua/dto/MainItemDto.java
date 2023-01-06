package com.shop.aqua.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {

	private Long id;

	private final String itemNm;

	private String categoryName;

	private String ItemNm;

	private String itemDetail;
	
	private String imgUrl;
	
	private Integer price;



	
	@QueryProjection // QueryDsl로 결과 조회시 MainItemDto 객체로 바로 받도록 하기 위함
	public MainItemDto(Long id, String categoryName,String itemNm, String itemDetail, String imgUrl, Integer price ) {

	this.categoryName= categoryName;
	this.id = id;
	this.itemNm = itemNm;
	this.itemDetail = itemDetail;
	this.imgUrl = imgUrl;
	this.price = price;
}

}
