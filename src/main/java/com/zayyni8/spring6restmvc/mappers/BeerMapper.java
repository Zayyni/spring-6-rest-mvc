package com.zayyni8.spring6restmvc.mappers;

import com.zayyni8.spring6restmvc.entities.Beer;
import com.zayyni8.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * CODED BY ZAYYNI
 */
@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
