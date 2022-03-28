package com.bside.afterschool.place.domain;

import com.bside.afterschool.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    private String caption;

    @Column(name = "post_image_url")
    private String postImageUrl;

    @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    // 좋아요
    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<Likes> likes;

    // 댓글
    @OrderBy("id DESC")
    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Transient  // DB에 컬럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient  // DB에 컬럼이 만들어지지 않는다.
    private Integer likeCount;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
