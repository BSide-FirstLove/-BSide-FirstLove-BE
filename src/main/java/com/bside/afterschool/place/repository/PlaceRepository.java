package com.bside.afterschool.place.repository;

import com.bside.afterschool.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
