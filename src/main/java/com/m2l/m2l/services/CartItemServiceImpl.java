package com.m2l.m2l.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2l.m2l.dto.ArticleUserDto;
import com.m2l.m2l.entities.CartItem;
import com.m2l.m2l.repositories.CartItemRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService{
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	public List<CartItem> findAll(){
		return cartItemRepository.findAll();
	}
	
	public CartItem save(@Valid CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	
	@Override
	public List<ArticleUserDto> findAllCartItemsByUser(String email){
		List<CartItem> allCartItems = cartItemRepository.findCartItemByUser(email);
		List<ArticleUserDto> cartItemsFilter = new ArrayList<ArticleUserDto>();
		for(int i = 0; i < allCartItems.size(); i++) {
			CartItem oneCartItem = allCartItems.get(i);
			cartItemsFilter.add(new ArticleUserDto(oneCartItem.getArticle(), oneCartItem.getQuantity()));
		}
		return cartItemsFilter;
	}
}