package com.bside.afterschool.place.domain;

import com.bside.afterschool.place.enumerate.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaceType name;        // 학교, 학원명, 추억의장소명

    @Column(nullable = false)
    private Double latitude;    // 위도

    @Column(nullable = false)
    private Double longitude;   // 경도

    // TODO 게시글 리스트


    // TODO 게시글의 댓글 리스트

}
