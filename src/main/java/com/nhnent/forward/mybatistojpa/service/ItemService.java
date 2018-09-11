package com.nhnent.forward.mybatistojpa.service;

import com.nhnent.forward.mybatistojpa.entity.Item;
import com.nhnent.forward.mybatistojpa.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;


    public Page<Item> getItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item getItem(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public Item createItem(Item item) {
        int count = itemMapper.insertItem(item);
        if (count != 1) {
            throw new RuntimeException("can't create item");
        }

        return item;
    }

    @Transactional
    public Item updateItem(Item item) {
        int count = itemMapper.updateItem(item);
        if (count != 1) {
            throw new RuntimeException("can't update item");
        }

        return getItem(item.getItemId());
    }

    @Transactional
    public boolean deleteItem(Long itemId) {
        return (itemMapper.deleteItem(itemId) == 1);
    }

}
