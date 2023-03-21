package com.zayyni8.spring6restmvc.services;

import com.zayyni8.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

/*
CODE BY ZAYYNI
* */
public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);


    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteBeerById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}
