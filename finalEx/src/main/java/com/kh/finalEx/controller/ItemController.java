package com.kh.finalEx.controller;

import com.kh.finalEx.constant.ItemSellStatus;
import com.kh.finalEx.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
public class ItemController {
    ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/new-item")
    public ResponseEntity<Boolean> itemCreate(@RequestBody Map<String, String> itemData) {
        String name = itemData.get("name");
        int price = Integer.parseInt(itemData.get("price"));
        String detail = itemData.get("detail");
        String status = itemData.get("status");
        int stock = Integer.parseInt(itemData.get("stock"));
        boolean result = itemService.createItem(name, price, detail, ItemSellStatus.SELL, stock);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
