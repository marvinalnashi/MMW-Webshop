package com.codearise.iprwcshop.api;

import com.codearise.iprwcshop.entity.ProductInfo;
import com.codearise.iprwcshop.service.CategoryService;
import com.codearise.iprwcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public Page<ProductInfo> getAllProducts(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.getAllProducts(request);
    }

    @GetMapping("/product/{productId}")
    public ProductInfo getProduct(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity addProduct(@Valid @RequestBody ProductInfo product,
                                     BindingResult bindingResult) {
        ProductInfo productIdExists = productService.getProduct(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "A product with this ID already exists");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity editProduct(@PathVariable("id") String productId,
                                      @Valid @RequestBody ProductInfo product,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("The product ID is incorrect");
        }
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity deleteProduct(@PathVariable("id") String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

}
