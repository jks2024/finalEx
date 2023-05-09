package com.kh.finalEx.service;

import com.kh.finalEx.constant.ItemSellStatus;
import com.kh.finalEx.dto.ItemDto;
import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.entity.Item;
import com.kh.finalEx.entity.Member;
import com.kh.finalEx.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 상품 생성
    public boolean createItem(String name, int price, String detail, ItemSellStatus status, int stock) {
        Item item = new Item();
        item.setItemNm(name);
        item.setPrice(price);
        item.setItemDetail(detail);
        item.setItemSellStatus(status);
        item.setStockNumber(stock);
        Item saveItem = itemRepository.save(item);
        log.info("저장된 상품 이름 : " + saveItem.getItemNm());

        return true;
    }
//    public boolean creatItemCheck(Item item) {
//        List<Item> items = itemRepository.findByItemNm(item.getItemNm());
//        return  true;
//    }

    // 상품 조회
    public List<ItemDto> getItemList() {
        List<Item> itemList;
        itemList = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemList) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm(item.getItemNm());
            itemDto.setPrice(item.getPrice());
            itemDto.setItemDetail(item.getItemDetail());
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }


}
