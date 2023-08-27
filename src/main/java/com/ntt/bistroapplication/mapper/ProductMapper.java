package com.ntt.bistroapplication.mapper;

import com.ntt.bistroapplication.domain.Product;
import com.ntt.bistroapplication.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    @Mapping(target = "id", ignore = true)
    Product productDTOtoProduct(ProductDTO productDTO);
}
