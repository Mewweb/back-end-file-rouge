package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.dto.ArticleUserDto;
import com.m2l.m2l.entities.CartItem;

public interface CartItemService {
	List<CartItem> findAll();
	List<CartItem> findAllTest(String email);
	CartItem save(CartItem cartItem);
	//Boolean update(List cartItems);
	Boolean update(List<ArticleUserDto> cartItems);
	List<ArticleUserDto> findAllCartItemsByUser(String email);
	Boolean remove(int id);
}