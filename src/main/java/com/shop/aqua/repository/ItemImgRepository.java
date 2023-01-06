package com.shop.aqua.repository;

import com.shop.aqua.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>{

	List<ItemImg> findByItemIdOrderByIdAsc(Long ItemId);

	ItemImg findByItemIdAndRepImgYn(Long ItemId, String repImgYn);

}
