package com.umc.workbook.apiPayload.exception.handler;

import com.umc.workbook.apiPayload.code.BaseErrorCode;
import com.umc.workbook.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}
