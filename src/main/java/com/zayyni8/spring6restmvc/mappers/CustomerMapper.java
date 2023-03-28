package com.zayyni8.spring6restmvc.mappers;

import com.zayyni8.spring6restmvc.entities.Customer;
import com.zayyni8.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * CODED BY ZAYYNI
 */

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}
