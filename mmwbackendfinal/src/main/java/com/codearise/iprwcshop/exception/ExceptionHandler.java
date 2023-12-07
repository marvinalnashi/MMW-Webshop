package com.codearise.iprwcshop.exception;

import com.codearise.iprwcshop.enums.ResultEnum;

public class ExceptionHandler extends RuntimeException {

    private Integer code;

    public ExceptionHandler(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public ExceptionHandler(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
