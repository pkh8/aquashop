package com.shop.aqua.repository;

import com.shop.aqua.dto.CartDetailDto;
import com.shop.aqua.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	// 장바구니에 담을 아이디와 상품
	CartItem findByCartIdAndItemId(Long cartId, Long itemId);

//		@Query(value="select new com.shop.aqua.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
//			"from CartItem ci, ItemImg im " +
//			"join ci.item i " +
//			"where ci.cart.id = :cartId and im.item.id = ci.item.id and im.repimgYn = 'Y' " +
//			"order by ci.regTime desc",nativeQuery = true)
//	List<CartDetailDto> findCartDetailDtoList(@Param("cartId")Long cartId);


	@Query("select new com.shop.aqua.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
			"from CartItem ci, ItemImg im " +
			"join ci.item i " +
			"where ci.cart.id = :cartId and im.item.id = ci.item.id and im.repImgYn = 'Y' " +
			"order by ci.regTime desc")
	List<CartDetailDto> findCartDetailDtoList(@Param("cartId")Long cartId);


}





//	@Query(value="select new com.shop.aqua.dto.CartDetailDto(cp.id, p.productName, p.price, cp.count, pm.imgUrl)" +
//			"from CartItem cp, ItemImg pm"+
//			"join cp.item.p" +
//			"where cp.cart.id = :cartId and pm.item.id = cp.item.id and im.repimgYn ='Y' " +
//			"order by cp.createAt desc",nativeQuery = true)
//	List<CartDetailDto> findCartDetailDtoList(Long cartId);
//}

