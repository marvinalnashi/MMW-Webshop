package com.codearise.iprwcshop.api;

import com.codearise.iprwcshop.entity.ProductCategory;
import com.codearise.iprwcshop.entity.ProductInfo;
import com.codearise.iprwcshop.service.CategoryService;
import com.codearise.iprwcshop.service.ProductService;
import com.codearise.iprwcshop.vo.response.CategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/category/{type}")
    public CategoryPage showCategory(@PathVariable("type") Integer categoryType,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "3") Integer size) {
        ProductCategory category = categoryService.getByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInCategory = productService.getProductsInCategory(categoryType, request);
        CategoryPage categoryPage = new CategoryPage("", productInCategory);
        categoryPage.setCategory(category.getCategoryName());
        return categoryPage;
    }
}