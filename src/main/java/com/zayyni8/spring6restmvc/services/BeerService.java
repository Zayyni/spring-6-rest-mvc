package com.zayyni8.spring6restmvc.services;

import com.zayyni8.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * CODE BY ZAYYNI
 */
public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);


    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
