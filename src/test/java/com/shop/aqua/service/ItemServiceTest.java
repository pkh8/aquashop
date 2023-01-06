package com.shop.aqua.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.shop.aqua.constant.ItemSellStatus;
import com.shop.aqua.dto.ItemFormDto;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.test.context.support.WithMockUser;


import com.shop.aqua.entity.Item;
import com.shop.aqua.entity.ItemImg;
import com.shop.aqua.repository.ItemImgRepository;
import com.shop.aqua.repository.ItemRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ItemServiceTest {

	@Autowired
	ItemService ItemService;


	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemImgRepository itemImgRepository;


	List<MultipartFile> createMultipartFiles() throws Exception{

		List<MultipartFile> multipartFilesList = new ArrayList<>();

		for(int i=0; i<5; i++) {
			String path ="C:/aquashop/Item/";
			String imageNm = "image" +i +".jpg";
			MockMultipartFile multipartFile =
					new MockMultipartFile(path, imageNm, "image/jpg", new byte[]{1,2,3,4});
			multipartFilesList.add(multipartFile);

		}
		return multipartFilesList;



	}

	@Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
	void saveItem() throws Exception{
		ItemFormDto itemFormDto = new ItemFormDto();
//		itemFormDto.setCategoryId("1");
		itemFormDto.setItemNm("테스트 상품");
		itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
		itemFormDto.setItemDetail("테스트 상품 설명");
        itemFormDto.getPrice();
        itemFormDto.getStockNumber();



		List<MultipartFile> multipartFilesList = createMultipartFiles();
		Long ItemId = ItemService.saveItem(itemFormDto, multipartFilesList);
		List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(ItemId);

		Item item = itemRepository.findById(ItemId)
		.orElseThrow(EntityNotFoundException::new);

//		assertEquals(itemFormDto.getCategoryId(), ItemService.getCategory());
		assertEquals(itemFormDto.getItemNm(), item.getItemNm());
		assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
		assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
		assertEquals(itemFormDto.getPrice(), item.getPrice());
		assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
		assertEquals(multipartFilesList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
	}



}
