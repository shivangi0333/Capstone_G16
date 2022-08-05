package com.wipro.capstoneshopfrohome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstoneshopfrohome.entity.Product;
import com.wipro.capstoneshopfrohome.entity.User;
import com.wipro.capstoneshopfrohome.entity.WishList;
import com.wipro.capstoneshopfrohome.service.IProductService;
import com.wipro.capstoneshopfrohome.service.IUserService;
import com.wipro.capstoneshopfrohome.service.IWishListService;

@RestController
@RequestMapping("/api/v1/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishLsitController {
	
	@Autowired
	private IWishListService wishListService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService productService;

	@GetMapping("/list/{email}")
	public Page<WishList> getWishList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,@PathVariable("email") String email) {
		PageRequest request = PageRequest.of(page - 1, size);
		Page<WishList> wishListPage;
		User user = userService.findOne(email);
		wishListPage = wishListService.findByBuyerEmail(user.getId(), request);
		return wishListPage;
	}

	@PostMapping("/add/{productId}/{email}")
	public ResponseEntity<WishList> addWishList(@PathVariable("productId") String productId,@PathVariable("email") String email) {
		Product product = productService.findOne(productId);
		User user = userService.findOne(email);
		WishList wishList = new WishList(user, product);
		WishList wishListCreated = wishListService.createWishlist(wishList);
		return new ResponseEntity<WishList>(wishListCreated, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{productId}/{email}")
	public ResponseEntity<Boolean> deletWishList(@PathVariable("productId") String productId,@PathVariable("email") String email) {
		User user = userService.findOne(email);
		Boolean wishListDeleted = wishListService.deleteWishlist(user, productId);
		return new ResponseEntity<Boolean>(wishListDeleted, HttpStatus.CREATED);
	}

	
}
