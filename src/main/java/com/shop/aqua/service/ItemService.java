package com.shop.aqua.service;

import com.shop.aqua.dto.*;
import com.shop.aqua.entity.Item;
import com.shop.aqua.entity.ItemImg;
import com.shop.aqua.entity.Member;
import com.shop.aqua.repository.ItemImgRepository;
import com.shop.aqua.repository.ItemRepository;
import com.shop.aqua.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;

    private final ItemImgService ItemImgService;



    private final ItemImgRepository itemImgRepository;


    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> ItemImgFileList) throws Exception{

        // 상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);


        // 이미지 등록
        for(int i=0; i<ItemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");

            ItemImgService.saveItemImg(itemImg, ItemImgFileList.get(i));
        }

        return item.getId();

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

//	public class CatalogService{
//		private final ItemRepository itemRepository;
//		public Page<Item> getItemByCategoryId(Long categoryId, int page,int size ){
//			List<Item> Items = itemRepository.findByCategoryId(Long categoryId, int page,int size){
//
//			}
//		}
//	}

//	@Transactional(rollbackFor = Exception.class)
//	public List<CategoryResult> getCategoryList() {
//		List<CategoryResult> results = categoryRepository.findAll().stream().map(CategoryResult::of).collect(Collectors.toList());
//		return results;
//	}

    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long ItemId) {
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(ItemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for(ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }
        Item item = itemRepository.findById(ItemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;

    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> ItemImgFileList) throws Exception{
        // 상품 수정
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> ItemImgIds = itemFormDto.getItemImgIds();

        // 이미지 등록
        for(int i = 0; i<ItemImgFileList.size(); i++) {
            ItemImgService.updateItemImg(ItemImgIds.get(i),
                    ItemImgFileList.get(i));
        }

        return item.getId();
    }


    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

    public Page<MainItemDto> getCategoryItemPage(ItemSearchDto itemSearchDto,String itemCategory, Pageable pageable){
        return itemRepository.getCategoryItemPage(itemSearchDto,itemCategory, pageable);
    }

}
