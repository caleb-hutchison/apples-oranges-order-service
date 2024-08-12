package com.api_demo.apples_oranges_order_service.controllers

import com.api_demo.apples_oranges_order_service.models.DiscountRequestDTO
import com.api_demo.apples_oranges_order_service.models.Order
import com.api_demo.apples_oranges_order_service.models.OrderRequestDTO
import com.api_demo.apples_oranges_order_service.models.OrderResponseDTO
import com.api_demo.apples_oranges_order_service.services.OrderService
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/api/orders")
class OrderController(val orderService: OrderService) {
    var enableAppleDiscount = false // buy one get one free
    var enableOrangeDiscount = false // 3 for the price of 2
    val priceApple = 0.6
    val priceOrange = 0.25

    @GetMapping("")
    fun getAllOrders(): List<Order> {
        return orderService.getAllOrders()
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: String): Order? {
        return orderService.getOrderById(id)
    }

    @PostMapping("/submit")
    fun submitOrder(
        @RequestBody inputOrderRequestDTO: OrderRequestDTO?
    ): OrderResponseDTO {
        return try {
            if (inputOrderRequestDTO == null) {
                throw IllegalArgumentException(
                    "Received null Order request.")
            }

            val numApplesRequested = inputOrderRequestDTO.numApples
            val numOrangesRequested = inputOrderRequestDTO.numOranges
            if (numApplesRequested < 0) {
                throw IllegalArgumentException(
                    "Number of Apples must be 0 or greater.")
            }
            if (numOrangesRequested < 0) {
                throw IllegalArgumentException(
                    "Number of Oranges must be 0 or greater.")
            }

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

            var mappedOrder = Order(
                id = null,
                discountForApplesApplied = enableAppleDiscount,
                discountForOrangesApplied = enableOrangeDiscount,
                numberOfApples = numApplesRequested,
                numberOfOranges = numOrangesRequested,
                priceForAllApples = applesTotalPrice,
                priceForAllOranges = orangesTotalPrice,
                priceTotal = applesTotalPrice + orangesTotalPrice
            )
            mappedOrder = orderService.save(mappedOrder)

            OrderResponseDTO(
                message = "Order Summary",
                order = mappedOrder
            )
        } catch (e: IllegalArgumentException) {
            OrderResponseDTO(
                message = e.message,
                order = null
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