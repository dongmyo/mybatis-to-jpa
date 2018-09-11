package com.nhnent.forward.mybatistojpa.repository;

import com.nhnent.forward.mybatistojpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
