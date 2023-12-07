package com.codearise.iprwcshop.repository;

import com.codearise.iprwcshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart, Integer> {
}
