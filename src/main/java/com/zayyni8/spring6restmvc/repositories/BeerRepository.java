package com.zayyni8.spring6restmvc.repositories;

import com.zayyni8.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/*CODED BY ZAYYNI
 * */

public interface BeerRepository extends JpaRepository<Beer, UUID> {


}
