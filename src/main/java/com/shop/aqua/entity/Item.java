package com.shop.aqua.entity;


import com.shop.aqua.constant.ItemSellStatus;
import com.shop.aqua.dto.ItemFormDto;
import com.shop.aqua.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)//키값을 생성하는 전략 명시 .AUTO : JPA 구현체가 자동으로 생성 전략을 결정
	private Long id; // 상품 코드


	@Column(nullable = false, length = 50) //필드와 컬럼을 매칭
	private String itemCategory;



	@Column(nullable = false, length = 50) //필드와 컬럼을 매칭
	private String itemNm;
	
	@Column(name="item_price", nullable = false)
	private int price; // 가격
	
	  @Column(nullable = false)
	  private int stockNumber;	//재고수량
	  
	  @Lob
	  @Column(nullable = false)
	  private String itemDetail; // 상품 상세설명

	  @Enumerated(EnumType.STRING) //Enum 타입 매칭
	  private ItemSellStatus itemSellStatus; //상품 판매 상태
	  


	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
		this.itemCategory = itemFormDto.getItemCategory();
	}
	  
	  public void removeStock(int stockNumber) {

		  int restStock =  this.stockNumber - stockNumber;
		  if(restStock < 0) {
			  throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량 : " + this.stockNumber+")");
		  }
		  this.stockNumber = restStock;

		  if(this.stockNumber <= 0){
			  this.itemSellStatus = ItemSellStatus.SOLD_OUT;
		  }
	  }





	public void addStock(int stockNumber) { this.stockNumber += stockNumber; }


}
