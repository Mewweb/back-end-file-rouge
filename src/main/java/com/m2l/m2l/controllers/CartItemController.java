package com.m2l.m2l.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.m2l.m2l.dto.ArticleUserDto;
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
}
