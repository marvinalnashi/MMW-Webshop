package com.codearise.iprwcshop.repository;

import com.codearise.iprwcshop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    List<ProductCategory> findAllByOrderByCategoryType();
    ProductCategory findByCategoryType(Integer categoryType);
}
