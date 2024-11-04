package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(nullable = false)
    private String storeName; // 가게 이름

    @Column(columnDefinition = "json")
    private String storeImage; // 가게 이미지 URL

    @Column(nullable = false)
    private String storeAddress; // 가게 주소

    @Column(nullable = false)
    private Double score; // 리뷰 평점

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isOpen; // 영업 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;// 지역과 다대일 단방향

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>(); // 리뷰와 일대다 양방향

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", sotreName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", score=" + score +
                ", region=" + (region != null ? region.getRegionName() : "N/A") + // region의 이름 출력
                '}';
    }
}
