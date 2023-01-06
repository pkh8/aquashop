package com.shop.aqua.dto;

import com.shop.aqua.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

	// 현재시간과 상품 등록일 비교해서 상품 데이터를 조회
	private String searchDateType;

	// 상품 판매상태 기준으로 상품 데이터를 조회
	private ItemSellStatus searchSellStatus;

	// 상품을 조회할때 어떤 유형으로 조회할지 선택
	// ex) productName : 상품명, createBy : 상품 등록자 아이디
	private String searchBy;

	private Long price;

	private String searchProductCategory;

	// 조회할 검색어 저장할 변수
	private String searchQuery="";


}
