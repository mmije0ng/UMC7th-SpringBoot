package com.umc.workbook.controller;

import com.umc.workbook.apiPayload.ApiResponse;
import com.umc.workbook.converter.TempConverter;
import com.umc.workbook.dto.TempResponse;
import com.umc.workbook.service.TempService.TempQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempRestController {
    private final TempQueryService tempQueryService;

    @Autowired
    public TempRestController(TempQueryService tempQueryService) {
        this.tempQueryService = tempQueryService;
    }

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        // c
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}
