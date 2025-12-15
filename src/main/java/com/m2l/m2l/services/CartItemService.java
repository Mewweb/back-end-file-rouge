package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.dto.ArticleUserDto;
import com.m2l.m2l.entities.CartItem;

public interface CartItemService {
	List<CartItem> findAll();
	CartItem save(CartItem cartItem);
	List<ArticleUserDto> findAllCartItemsByUser(String email);
}