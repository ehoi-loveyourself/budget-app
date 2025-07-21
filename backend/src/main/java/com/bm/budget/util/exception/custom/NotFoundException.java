package com.bm.budget.util.exception.custom;

import com.bm.budget.util.exception.BusinessException;
import com.bm.budget.util.exception.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
