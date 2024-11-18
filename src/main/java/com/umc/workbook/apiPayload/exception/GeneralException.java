package com.umc.workbook.apiPayload.exception;

import com.umc.workbook.apiPayload.code.BaseErrorCode;
import com.umc.workbook.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason(){
        // ErrorReasonDTO의 reason, message, isSuccess
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        // ErrorReasonDTO의 httpStatus, reason, message, isSuccess
        return this.code.getReasonHttpStatus();
    }
}
