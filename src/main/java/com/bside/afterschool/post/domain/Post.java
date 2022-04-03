package com.bside.afterschool.post.domain;

import com.bside.afterschool.common.domain.BaseEntity;
import com.bside.afterschool.place.domain.Place;
import com.bside.afterschool.place.enumerate.PlaceType;
import com.bside.afterschool.post.dto.CreatePostRequest;
import com.bside.afterschool.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PlaceType placeType;

    // TODO length 기획정의 필요
    @Column(nullable = false, length = 200)
    private String contents;

    @Column(name = "year")
    private String year;

    // TODO 이미지, 해시태그
    //    @OneToMany(mappedBy = "post")
    //    private List<String> imgPathList = new ArrayList<>();   // 이미지 최대 3개
    //    private List<String> hashtagList = new ArrayList<>();   // 해시태그 최대 3개

    // TODO 좋아요, 댓글

    @Builder
    public void createPost(CreatePostRequest createPostRequest, User user, Place place) {
        this.contents = createPostRequest.getContents();
        this.year = createPostRequest.getYear();
        this.user = user;
        this.place = place;
        // TODO this.imgPath = createPostRequest.getImgPathList();
        // TODO this.hashtag = createPostRequest.getHashtagList();
        user.getPostList().add(this);
        place.getPostList().add(this);
    }

}
