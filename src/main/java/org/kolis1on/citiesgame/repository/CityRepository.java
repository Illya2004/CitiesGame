package org.kolis1on.citiesgame.repository;

import org.kolis1on.citiesgame.entity.City;
import org.kolis1on.citiesgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByCity (String city);
    List<City> findAllByPlayer(Player player);

    void deleteByPlayer (Player player);
}
