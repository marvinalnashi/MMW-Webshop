package com.codearise.iprwcshop.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {


    PARAM_ERROR(1, "Parameter error"),
    PRODUCT_NOT_EXIST(10, "This product doesn't exist"),
    PRODUCT_NOT_ENOUGH(11, "There aren't enough of this product in stock"),
    PRODUCT_STATUS_ERROR(12, "This product's status is incorrect"),
    PRODUCT_OFF_SALE(13,"This product is discounted"),
    PRODUCT_NOT_IN_CART(14,"This product isn't in the shopping cart"),
    CART_CHECKOUT_SUCCESS(20, "The transaction was successful"),
    CATEGORY_NOT_EXIST(30, "This category doesn't exist"),
    USER_NOT_EXIST(40,"This user doesn't exist"),
    VALID_ERROR(50, "The provided information is invalid"),
    ORDER_NOT_EXIST(60, "This order doesn't exist"),
    ORDER_STATUS_ERROR(61, "This order's status is incorrect");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

