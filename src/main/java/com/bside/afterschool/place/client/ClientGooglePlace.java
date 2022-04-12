package com.bside.afterschool.place.client;

import com.bside.afterschool.auth.exception.TokenValidFailedException;
import com.bside.afterschool.place.domain.Place;
import com.bside.afterschool.place.dto.GooglePlaceResponse;
import com.bside.afterschool.place.dto.PlaceRequest;
import com.bside.afterschool.place.enumerate.PlaceType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientGooglePlace {

    @Value("${google.key-secert}")
    private String googlePlaceKey;

    @Autowired
    private WebClient webClient;

    /**
     *
     * @param placeRequest
     * @return
     */
    public Place getPlaceData(PlaceRequest placeRequest) {
        GooglePlaceResponse googlePlaceResponse = webClient.get()
                .uri("https://maps.googleapis.com/maps/api/place/details/json", builder -> builder
                        .queryParam("place_id", placeRequest.getPlaceId())
                        .queryParam("key", googlePlaceKey)
                        .queryParam("language", "ko").build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("place_id Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(GooglePlaceResponse.class)
                .block();

        // 장소저장
        return Place.builder()
                .googlePlaceId(googlePlaceResponse.getResult().getPlaceId())
                .placeName(googlePlaceResponse.getResult().getName())
                 .latitude(googlePlaceResponse.getResult().getGeometry().getLocation().getLat())
                .longitude(googlePlaceResponse.getResult().getGeometry().getLocation().getLng())
                .user(placeRequest.getUser())
                .placeType(PlaceType.SCHOOL)    // TODO 현재 학교로 하드코딩
                .build();
    }
}
