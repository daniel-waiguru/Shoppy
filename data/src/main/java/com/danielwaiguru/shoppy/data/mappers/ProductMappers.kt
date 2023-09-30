package com.danielwaiguru.shoppy.data.mappers

import com.danielwaiguru.shoppy.data.models.dtos.ProductDto
import com.danielwaiguru.shoppy.domain.models.Product

internal fun ProductDto.toProduct(): Product =
    Product(
        currencyCode = currencyCode,
        description = description,
        id = id,
        imageLocation = imageLocation,
        name = name,
        price = price,
        quantity = quantity
    )