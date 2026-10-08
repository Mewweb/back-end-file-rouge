package com.m2l.m2l.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.m2l.m2l.dto.ArticleUserDto;
import com.m2l.m2l.dto.CreateCartItem;
import com.m2l.m2l.entities.Article;
import com.m2l.m2l.entities.CartItem;
import com.m2l.m2l.entities.User;
import com.m2l.m2l.repositories.ArticleRepository;
import com.m2l.m2l.repositories.CartItemRepository;
import com.m2l.m2l.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<CartItem> findAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem saveCartItem(CreateCartItem newCartItem) {
		User user = userRepository.findByEmail(newCartItem.getUser());
		Article article = articleRepository.findById(newCartItem.getArticle())
				.orElseThrow(() -> new RuntimeException("Article non trouvé"));
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(newCartItem.getQuantity());
		cartItem.setArticle(article);
		cartItem.setUser(user);
		return cartItemRepository.save(cartItem);
	}

	@Override
	public List<ArticleUserDto> findAllCartItemsByUser(String email) {
		List<CartItem> allCartItems = cartItemRepository.findCartItemByUser(email);
		List<ArticleUserDto> cartItemsFilter = new ArrayList<ArticleUserDto>();
		for (int i = 0; i < allCartItems.size(); i++) {
			CartItem oneCartItem = allCartItems.get(i);
			cartItemsFilter
					.add(new ArticleUserDto(oneCartItem.getId(), oneCartItem.getArticle(), oneCartItem.getQuantity()));
		}
		return cartItemsFilter;
	}

	@Override
	public Boolean update(List<ArticleUserDto> cartItems) {
		for (ArticleUserDto element : cartItems) {
			Optional<CartItem> testCartItem = cartItemRepository.findById(element.getId());
			testCartItem.get().setQuantity(element.getQuantity());
			cartItemRepository.save(testCartItem.get());
		}
		return true;
	}

	@Override
	public List<CartItem> findAllTest(String email) {
		List<CartItem> allCartItems = cartItemRepository.findCartItemByUser(email);
		return allCartItems;
	}

	@Override
	public Boolean remove(int id) {
		CartItem cartItem = cartItemRepository.findById(id).orElse(null);
		if (cartItem != null) {
			cartItemRepository.deleteById(id);
			return true;
		}
		return false;
	}
}