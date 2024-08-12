package com.api_demo.apples_oranges_order_service.models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class OrderResponseDTOTest {
    private lateinit var testOrder: Order
    private lateinit var testResponse: OrderResponseDTO
    private var testMessage = "test message"

    @BeforeEach
    fun setUp() {
        testOrder = Order(
            id = "test-id",
            discountForApplesApplied = false,
            discountForOrangesApplied = true,
            numberOfApples = 4,
            numberOfOranges = 10,
            priceForAllApples = 2.4,
            priceForAllOranges = 1.75,
            priceTotal = 4.15
        )
        testResponse = OrderResponseDTO(
            message = testMessage,
            order = testOrder
        )
    }

    @Test
    fun getMessage() {
        assertEquals(testMessage, testResponse.message)
    }

    @Test
    fun getOrder() {
        assertEquals(testOrder, testResponse.order)
    }
}
