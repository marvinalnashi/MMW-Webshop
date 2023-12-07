package com.codearise.iprwcshop.api;

import com.codearise.iprwcshop.entity.Cart;
import com.codearise.iprwcshop.entity.ProductInOrder;
import com.codearise.iprwcshop.entity.ProductInfo;
import com.codearise.iprwcshop.entity.User;
import com.codearise.iprwcshop.form.ItemForm;
import com.codearise.iprwcshop.repository.ProductInOrderRepository;
import com.codearise.iprwcshop.service.CartService;
import com.codearise.iprwcshop.service.ProductInOrderService;
import com.codearise.iprwcshop.service.ProductService;
import com.codearise.iprwcshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductInOrderService productInOrderService;

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<Cart> linkCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        User user = userService.getUser(principal.getName());
        cartService.linkUserWithCart(productInOrders, user);
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        User user = userService.getUser(principal.getName());
        return cartService.getCart(user);
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        ProductInfo productInfo = productService.getProduct(form.getProductId());
        try {
            linkCart(Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.getUser(principal.getName());
        productInOrderService.update(itemId, quantity, user);
        return productInOrderService.getProductInOrder(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        User user = userService.getUser(principal.getName());
        cartService.deleteItem(itemId, user);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal) {
        User user = userService.getUser(principal.getName());
        cartService.checkoutCurrentCart(user);
        return ResponseEntity.ok(null);
    }
}
