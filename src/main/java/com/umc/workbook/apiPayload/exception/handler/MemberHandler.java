package com.umc.workbook.apiPayload.exception.handler;

import com.umc.workbook.apiPayload.code.BaseErrorCode;
import com.umc.workbook.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
