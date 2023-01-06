package com.shop.aqua.dto;


import com.shop.aqua.constant.ItemSellStatus;
import com.shop.aqua.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto { // 상품 데이터 정보를 전달하는 Dto class
	
	private Long id;
	
	@NotBlank(message = "카테고리를 선택해주세요")
	private String itemCategory;
	
	@NotBlank(message ="상품명은 필수값입니다.")
	private String itemNm;

	@NotNull(message ="가격은 필수 입력 값입니다.")
	private Integer price;
	
	@NotBlank(message = "상품 상세는 필수 입력값입니다.")
	private String itemDetail;
	
	@NotNull(message ="재고는 필수 입력 값입니다..")
	private Integer stockNumber;
	
	private ItemSellStatus itemSellStatus;
	
	private List<ItemImgDto> itemImgDtoList = new ArrayList(); //상품 저장 후 수정할 때 상품 이미지 정보 저장 리스트
	
	private List<Long> itemImgIds = new ArrayList(); // 상품의 이미지, 아이디를 저장하는 리스트
	
    private  static ModelMapper modelMapper = new ModelMapper();
	
    public Item createItem() {
    	return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
    	return modelMapper.map(item, ItemFormDto.class);
    }
    
}
