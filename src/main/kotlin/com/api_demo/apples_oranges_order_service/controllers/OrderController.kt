package com.api_demo.apples_oranges_order_service.controllers

import com.api_demo.apples_oranges_order_service.models.DiscountRequestDTO
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
    var enableAppleDiscount = false // buy one get one free
    var enableOrangeDiscount = false // 3 for the price of 2
    val priceApple = 0.6
    val priceOrange = 0.25

    @PostMapping("/submit")
    fun submitOrder(
        @RequestBody inputOrderRequestDTO: OrderRequestDTO?
    ): OrderResponseDTO {
        return try {
            if (inputOrderRequestDTO == null) {
                throw IllegalArgumentException("Received null Order request.")
            }

            val numApplesRequested = inputOrderRequestDTO.numApples
            val numOrangesRequested = inputOrderRequestDTO.numOranges

            val applesTotalPrice: Double
            val orangesTotalPrice: Double

            if (enableAppleDiscount) {
                val numApplesForCalculation = (numApplesRequested / 2) + (numApplesRequested % 2)
                applesTotalPrice = numApplesForCalculation * priceApple
            }
            else {
                applesTotalPrice = numApplesRequested * priceApple
            }

            if (enableOrangeDiscount) {
                val numOrangesForCalculation = (numOrangesRequested / 3 * 2) + (numOrangesRequested % 3)
                orangesTotalPrice = numOrangesForCalculation * priceOrange
            }
            else {
                orangesTotalPrice = numOrangesRequested * priceOrange
            }

            OrderResponseDTO(
                message = "Order Summary",
                orderRequest = inputOrderRequestDTO,
                priceForAllApples = applesTotalPrice,
                priceForAllOranges = orangesTotalPrice
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

    @PostMapping("/toggleDiscounts")
    fun toggleDiscounts(
        @RequestBody inputDiscountRequestDTO: DiscountRequestDTO
    ): String {
        enableAppleDiscount = inputDiscountRequestDTO.appleDiscountEnabled
        enableOrangeDiscount = inputDiscountRequestDTO.orangeDiscountEnabled
        return """
                Apple Discount Enabled: $enableAppleDiscount
                Orange Discount Enabled: $enableOrangeDiscount
            """.trimIndent()
    }
}