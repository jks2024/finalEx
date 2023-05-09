package com.kh.finalEx.controller;

import com.kh.finalEx.constant.ItemSellStatus;
import com.kh.finalEx.dto.ItemDto;
import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/item")
public class ItemController {
    ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    //  제품 등록
    @PostMapping(value= "/new")
    public ResponseEntity<Boolean> itemCreate(@RequestBody Map<String, String> itemData) {
        String name = itemData.get("name");
        int price = Integer.parseInt(itemData.get("price"));
        String detail = itemData.get("detail");
        int stock = Integer.parseInt(itemData.get("stock"));
        String status = itemData.get("status");
        boolean result = itemService.createItem(name, price, detail, ItemSellStatus.SELL, stock);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    // 제품 조회
    @GetMapping(value = "/get")
    public ResponseEntity<List<ItemDto>> itemList(@RequestParam String name) {
        List<ItemDto> list = itemService.getItemList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
