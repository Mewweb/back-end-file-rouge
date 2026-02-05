package com.m2l.m2l.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.m2l.m2l.dto.ArticleUserDto;
import com.m2l.m2l.dto.CreateCartItem;
import com.m2l.m2l.entities.CartItem;
import com.m2l.m2l.services.CartItemService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cartItem")
@AllArgsConstructor
public class CartItemController {
	private CartItemService cartItemService;

	@GetMapping("/panier/{email}")
	public ResponseEntity<List<ArticleUserDto>> getCartItems(@PathVariable String email){
		var cartItems = cartItemService.findAllCartItemsByUser(email);
		if(cartItems == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<ArticleUserDto>>(cartItems, HttpStatus.OK);
	}
	
	@PostMapping("/panier/add")
	@ResponseStatus(HttpStatus.CREATED)
	public CartItem addCartItem(@RequestBody CreateCartItem cartItem){
		return cartItemService.saveCartItem(cartItem);
	}
	
	@PutMapping("/panier/update")
	public ResponseEntity<Void> updateCartItems(@RequestBody List<ArticleUserDto> cartItems){
		Boolean c = cartItemService.update(cartItems);
		if(c == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable int id){
		var c = cartItemService.remove(id);
		if(c == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
}
