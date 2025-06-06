package com.AccionesUD.AccionesUD.application.orders;

import org.springframework.data.repository.query.Param;

import com.AccionesUD.AccionesUD.dto.orders.OrderRequestDTO;
import com.AccionesUD.AccionesUD.dto.orders.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO requestDTO);
}
