package com.shop.aqua.controller;



import com.shop.aqua.dto.ItemSearchDto;

import com.shop.aqua.dto.MainItemDto;
import com.shop.aqua.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MainController {


	private final ItemService itemService;


	//메인 페이지 이동
	@GetMapping(value="/")
	public String main1(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {

		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 8);
		Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

		model.addAttribute("items", items);
		model.addAttribute("itemSearchDto", itemSearchDto);
		model.addAttribute("maxPage", 5);
		return"main";
	}


	//상품 전체 리스트 화면으로 이동
	@GetMapping("/itemList")
	public String itemList(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 16 );
		Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

		model.addAttribute("items", items);
		model.addAttribute("itemSearchDto", itemSearchDto);
		model.addAttribute("maxPage", 10);

		return "item/itemList";
	}



	//상품 카테고리별로 화면 이동
	@GetMapping(value = "/category/{itemCategory}")
	public String itemCategory(@PathVariable("itemCategory")String itemCategory, ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 8);
		Page<MainItemDto> items = itemService.getCategoryItemPage(itemSearchDto,itemCategory ,pageable);

		model.addAttribute("items", items);
		model.addAttribute("itemSearchDto", itemSearchDto);
		model.addAttribute("maxPage", 5);

		return "item/itemList";
//		return "item/itemCategoryList";
	}


	@GetMapping(value = "/index")
		public String index () {
			return "index";
		}

		@GetMapping(value ="/list")
	public String list(){
		return "item/list";
		}



}







