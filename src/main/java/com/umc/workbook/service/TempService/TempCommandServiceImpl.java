package com.umc.workbook.service.TempService;

import com.umc.workbook.apiPayload.code.status.ErrorStatus;
import com.umc.workbook.apiPayload.exception.handler.TempHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempQueryService{
    @Override
    public void CheckFlag(Integer flag) {
        if(flag==1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
