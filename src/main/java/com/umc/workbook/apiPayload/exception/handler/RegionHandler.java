package com.umc.workbook.apiPayload.exception.handler;

import com.umc.workbook.apiPayload.code.BaseErrorCode;
import com.umc.workbook.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode code) {
        super(code);
    }
}
