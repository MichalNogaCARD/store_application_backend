package com.app.mappers;

import com.app.dto.CartDTO;
import com.app.model.Cart;
import com.app.model.Product;
import com.app.repository.ProductsInCartRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

public interface CartMapper {

    static CartDTO toDto(Cart cart) {
        return cart == null ? null : CartDTO.builder()
                .id(cart.getId())
                .cartClosed(cart.getCartClosed())
                .userDTO(cart.getUser() == null ? null : UserMapper.toDto(cart.getUser()))
                .totalGrossValue(cart.getTotalGrossValue())
                .totalNetValue(cart.getTotalNetValue())
                .totalVatValue(cart.getTotalVatValue())
                .deliveryAddressDTO(cart.getDeliveryAddress() == null ?
                        null : DeliveryAddressMapper.toDto(cart.getDeliveryAddress()))
                .purchaseTime(cart.getPurchaseTime())
                .orderNumber(cart.getOrderNumber())
                .productsInCartDTO(cart.getProductsInCart() == null ? null : cart.getProductsInCart()
                        .stream().map(ProductInCartMapper::toDto).collect(Collectors.toSet()))
                .build();
    }

    static Cart fromDto(CartDTO cartDTO) {
        return cartDTO == null ? null : Cart.builder()
                .id(cartDTO.getId())
                .cartClosed(cartDTO.getCartClosed())
                .user(cartDTO.getUserDTO() == null ? null : UserMapper.fromDto(cartDTO.getUserDTO()))
                .totalGrossValue(cartDTO.getTotalGrossValue())
                .totalNetValue(cartDTO.getTotalNetValue())
                .totalVatValue(cartDTO.getTotalVatValue())
                .deliveryAddress(cartDTO.getDeliveryAddressDTO() == null ?
                        null : DeliveryAddressMapper.fromDto(cartDTO.getDeliveryAddressDTO()))
                .purchaseTime(cartDTO.getPurchaseTime())
                .orderNumber(cartDTO.getOrderNumber())
                .build();
    }
}