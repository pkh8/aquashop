package com.shop.aqua.dto;

import com.shop.aqua.entity.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {



	//뷰로부터 전달받을 값
	private Long itemId;

	private int count;


	//DB로부터 꺼내올 값
	private String itemNm;
	
	private Integer itemPrice;

	private String itemDetail;

	private String imgUrl;
	
	//만들어낼 값
	private int orderPrice;

	private int totalPrice;



	public OrderItemDto(OrderItem orderItem, String imgUrl) {
		this.itemNm = orderItem.getItem().getItemNm();
		this.count = orderItem.getCount();
		this.orderPrice = orderItem.getOrderPrice();
		this.imgUrl = imgUrl;

	}



}
