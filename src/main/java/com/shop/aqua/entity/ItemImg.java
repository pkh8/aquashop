package com.shop.aqua.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter
@Setter
public class ItemImg extends BaseEntity{

	@Id
	@Column(name="item_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName; // 이미지 파일명
	
	private String oriImgName; // 원문 이미지 파일명
	
	private String imgUrl; // 이미지 조회 경로
	
	private String repImgYn; // 대표 이미지 여부 Y인 경우 메인 페이지에서 상품을 보여줄때 사용
							// N일 때는 사용하지 않는다.

		@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
		@JoinColumn(name = "item_id")
		@OnDelete(action = OnDeleteAction.CASCADE)
	   private Item item;
	   
	   
	   
	   public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		   this.oriImgName = oriImgName;
		   this.imgName = imgName;
		   this.imgUrl = imgUrl;
		   
	   }
	   
	   
	   


}
