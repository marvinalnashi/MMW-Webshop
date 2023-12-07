package com.codearise.iprwcshop.service;

import com.codearise.iprwcshop.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductInfo getProduct(String productId);
    Page<ProductInfo> findUpAll(Pageable pageable);
    Page<ProductInfo> getAllProducts(Pageable pageable);
    Page<ProductInfo> getProductsInCategory(Integer categoryType, Pageable pageable);
    void increaseStock(String productId, int amount);
    void decreaseStock(String productId, int amount);
    ProductInfo updateProduct(ProductInfo productInfo);
    ProductInfo saveProduct(ProductInfo productInfo);
    void deleteProduct(String productId);


}
