package com.ntt.bistroapplication.mapper;

import com.ntt.bistroapplication.model.Customer;
import com.ntt.bistroapplication.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(target = "id", ignore = true)
    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
