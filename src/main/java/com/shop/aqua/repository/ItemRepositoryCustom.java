
package com.shop.aqua.repository;


import com.shop.aqua.dto.MainItemDto;
import com.shop.aqua.dto.ItemSearchDto;
import com.shop.aqua.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

	Page<MainItemDto> getCategoryItemPage(ItemSearchDto itemSearchDto, String itemCategory, Pageable pageable);
}

