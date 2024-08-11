package com.api_demo.apples_oranges_order_service.controllers

import com.api_demo.apples_oranges_order_service.models.OrderRequestDTO
import com.api_demo.apples_oranges_order_service.models.OrderResponseDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/api/orders")
class OrderController {
    val priceApple : Double = 0.6;
    val priceOrange : Double = 0.25;

    @PostMapping("/submit")
    fun submitOrder(
        @RequestBody inputOrderRequestDTO: OrderRequestDTO?
    ): OrderResponseDTO {
        return try {
            if (inputOrderRequestDTO == null) {
                throw IllegalArgumentException("Received null Order request.")
            }
            OrderResponseDTO(
                message = "Order Summary",
                orderRequest = inputOrderRequestDTO,
                priceForAllApples = inputOrderRequestDTO.numApples * priceApple,
                priceForAllOranges = inputOrderRequestDTO.numOranges * priceOrange
            )
        } catch (e: IllegalArgumentException) {
            OrderResponseDTO(
                message = e.message,
                orderRequest = null,
                priceForAllApples = 0.0,
                priceForAllOranges = 0.0
            )
        }
    }
}