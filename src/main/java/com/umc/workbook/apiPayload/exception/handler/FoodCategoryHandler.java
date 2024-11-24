package com.umc.workbook.apiPayload.exception.handler;

import com.umc.workbook.apiPayload.code.BaseErrorCode;
import com.umc.workbook.apiPayload.exception.GeneralException;

// 음식 카테고리 관련 핸들러
public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode code) {
        super(code);
    }
}
