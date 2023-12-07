package com.codearise.iprwcshop.service.impl;

import com.codearise.iprwcshop.entity.ProductInfo;
import com.codearise.iprwcshop.enums.ProductStatusEnum;
import com.codearise.iprwcshop.enums.ResultEnum;
import com.codearise.iprwcshop.exception.ExceptionHandler;
import com.codearise.iprwcshop.repository.ProductInfoRepository;
import com.codearise.iprwcshop.service.CategoryService;
import com.codearise.iprwcshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public ProductInfo getProduct(String productId) {
        return productInfoRepository.findByProductId(productId);
    }

    @Override
    public Page<ProductInfo> findUpAll(Pageable pageable) {
        return productInfoRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(),pageable);
    }

    @Override
    public Page<ProductInfo> getAllProducts(Pageable pageable) {
        return productInfoRepository.findAllByOrderByProductId(pageable);
    }

    @Override
    public Page<ProductInfo> getProductsInCategory(Integer categoryType, Pageable pageable) {
        return productInfoRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(String productId, int amount) {
        ProductInfo productInfo = getProduct(productId);
        if (productInfo == null) throw new ExceptionHandler(ResultEnum.PRODUCT_NOT_EXIST);
        int update = productInfo.getProductStock() + amount;
        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(String productId, int amount) {
        ProductInfo productInfo = getProduct(productId);
        if (productInfo == null) throw new ExceptionHandler(ResultEnum.PRODUCT_NOT_EXIST);
        int update = productInfo.getProductStock() - amount;
        if(update <= 0) throw new ExceptionHandler(ResultEnum.PRODUCT_NOT_ENOUGH );
        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo updateProduct(ProductInfo productInfo) {
        categoryService.getByCategoryType(productInfo.getCategoryType());
        if(productInfo.getProductStatus() > 1) {
            throw new ExceptionHandler(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo saveProduct(ProductInfo productInfo) {
        return updateProduct(productInfo);
    }

    @Override
    public void deleteProduct(String productId) {
        ProductInfo productInfo = getProduct(productId);
        if (productInfo == null) throw new ExceptionHandler(ResultEnum.PRODUCT_NOT_EXIST);
        productInfoRepository.delete(productInfo);
    }
}
