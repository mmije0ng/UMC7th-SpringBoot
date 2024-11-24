package com.umc.workbook.domain;

import com.umc.workbook.domain.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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

    @Column(columnDefinition = "DOUBLE DEFAULT 0")
    private Double score = 0.0; // 리뷰 평점

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isOpen = false; // 영업 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;// 지역과 다대일 단방향

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>(); // 리뷰와 일대다 양방향

    // 지역과 양방향 연관관계 추가
    public void setRegion(Region region){
        // 기존 연관된 지역에서 현재 객체 제거
        if (this.region != null)
            this.region.getStoreList().remove(this);

        // 새로운 지역 설정
        this.region = region;

        // 새로운 지역의 가게 리스트에 현재 객체 추가
        if (region != null)
            region.getStoreList().add(this);
    }

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
