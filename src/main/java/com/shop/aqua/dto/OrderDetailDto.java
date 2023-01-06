package com.shop.aqua.dto;

public class OrderDetailDto {


    private Long orderItemId;

    private String itemNm;

    private int price;

    private int count;

    private String imgUrl;

    public OrderDetailDto(Long orderItemId, String itemNm, int price, int count, String imgUrl){
        this.orderItemId = orderItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
