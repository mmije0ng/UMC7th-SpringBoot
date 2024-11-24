package com.umc.workbook;

import com.umc.workbook.dto.ReviewDto;
import com.umc.workbook.service.MissionService.MissionQueryService;
import com.umc.workbook.service.ReviewService.ReviewQueryService;
import com.umc.workbook.service.StoreService.StoreQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			// 파라미터 값 설정
//			String storeName = "요아정";
//			Float score = 4.0f;
//
//			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("StoreName: " + storeName);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(storeName, score)
//					.forEach(System.out::println);
//
////			// 리뷰 작성
////			ReviewQueryService reviewQueryService = context.getBean(ReviewQueryService.class);
////			Long memberId = 1L;
////			Long storeId = 1L;
////
////			List<String> reviewImageList = new ArrayList<>();
////			reviewImageList.add("https://i.pinimg.com/564x/48/3d/a7/483da78ca17fa011004bac70b7e7c763.jpg");
////			reviewImageList.add("https://i.pinimg.com/564x/38/73/51/387351a404a2dcf47ada6a138b7a14e7.jpg");
////
////			ReviewDto.InsertRequest requestDto = ReviewDto.InsertRequest
////					.builder()
////					.reviewScore(4.5)
////					.reviewContent("너무 맛있게 먹었어요")
////					.reviewImageList(reviewImageList)
////					.build();
////			// 리뷰 등록 서비스 메서드
////			reviewQueryService.registerReview(memberId, storeId, requestDto);
//		};
//	}
}
