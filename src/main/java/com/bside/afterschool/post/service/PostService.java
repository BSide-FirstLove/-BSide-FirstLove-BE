package com.bside.afterschool.post.service;

import com.bside.afterschool.common.exception.global.error.exception.BusinessException;
import com.bside.afterschool.common.exception.global.error.exception.ErrorCode;
import com.bside.afterschool.place.domain.Place;
import com.bside.afterschool.post.domain.Post;
import com.bside.afterschool.post.dto.CreatePostRequest;
import com.bside.afterschool.post.repository.PostQuerydslRepository;
import com.bside.afterschool.post.repository.PostRepository;
import com.bside.afterschool.user.domain.User;
import com.bside.afterschool.user.repository.UserRepository;
import com.bside.afterschool.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PostQuerydslRepository postQuerydslRepository;
    private final PostRepository postRepository;

    /**
     * 장소별 게시글 등록
     * @param token
     * @param placeId
     * @param createPostRequest
     */
    @Transactional
    public void registerPost(String token, Long placeId, CreatePostRequest createPostRequest) {
        if(ObjectUtils.isEmpty(createPostRequest.getPlaceType())) {
            throw new BusinessException("존재하지 않는 PlaceType입니다.", ErrorCode.INVALID_INPUT_VALUE);
        }

        Long userId = userService.getUserId(token);
        Optional<Place> place = null; // TODO 장소추가되면 장소조회 추가 필요 postRepository.findById(placeId);
        Optional<User> user = userRepository.findById(userId);

        Post newPost = new Post(user.orElseThrow(NullPointerException::new), place.orElseThrow(NullPointerException::new), createPostRequest.getPlaceType());
        postRepository.save(newPost);
    }

}
