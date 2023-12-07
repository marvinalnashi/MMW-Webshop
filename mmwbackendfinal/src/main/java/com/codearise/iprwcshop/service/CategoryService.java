package com.codearise.iprwcshop.service;

import com.codearise.iprwcshop.entity.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory getByCategoryType(Integer categoryType);
    List<ProductCategory> getByCategoryTypeList(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
