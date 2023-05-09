package com.kh.finalEx.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="item_img")
@Getter @Setter
public class ItemImg {
    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    // 상품 엔티티와 다대일 단방향 관계로 매핑합니다. 지연 로딩을 설정하여 매핑된 상품 엔티티 정보가 필요할 경우 데이터를 조회
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
