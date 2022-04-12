package com.bside.afterschool.place.domain;

import com.bside.afterschool.common.domain.BaseEntity;
import com.bside.afterschool.place.enumerate.PlaceType;
import com.bside.afterschool.post.domain.Post;
import com.bside.afterschool.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Place extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    // user JOIN
    @JsonIgnoreProperties({"place"})
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "google_place_id")
    private String googlePlaceId;  // 구글 장소ID placeId

    @Column(name = "place_name")
    private String placeName;  // 장소명

    @Column(name = "place_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;    // 장소타입

    @Column(nullable = false)
    private String latitude;    // 위도

    @Column(nullable = false)
    private String longitude;   // 경도

    // TODO 게시글 리스트
    @OneToMany(mappedBy = "place")
    private List<Post> postList = new ArrayList<>();

    // TODO 게시글의 댓글 리스트

}
