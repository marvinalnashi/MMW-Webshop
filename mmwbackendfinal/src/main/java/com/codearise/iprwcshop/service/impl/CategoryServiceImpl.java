package com.codearise.iprwcshop.service.impl;

import com.codearise.iprwcshop.entity.ProductCategory;
import com.codearise.iprwcshop.enums.ResultEnum;
import com.codearise.iprwcshop.exception.ExceptionHandler;
import com.codearise.iprwcshop.repository.ProductCategoryRepository;
import com.codearise.iprwcshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAllByOrderByCategoryType();
    }

    @Override
    public ProductCategory getByCategoryType(Integer categoryType) {
        ProductCategory res = productCategoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new ExceptionHandler(ResultEnum.CATEGORY_NOT_EXIST);
        return res;
    }

    @Override
    public List<ProductCategory> getByCategoryTypeList(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
    }

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
