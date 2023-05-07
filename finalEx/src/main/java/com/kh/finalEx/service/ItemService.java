package com.kh.finalEx.service;

import com.kh.finalEx.constant.ItemSellStatus;
import com.kh.finalEx.entity.Item;
import com.kh.finalEx.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        itemRepository.save(item);
        return true;
    }
//    public boolean creatItemCheck(Item item) {
//        List<Item> items = itemRepository.findByItemNm(item.getItemNm());
//        return  true;
//    }

}
