package com.api_demo.apples_oranges_order_service.controllers

import com.api_demo.apples_oranges_order_service.models.OrderRequestDTO
import com.api_demo.apples_oranges_order_service.models.OrderResponseDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class OrderControllerTest {
    private lateinit var mockRequest: OrderRequestDTO
    private lateinit var expectedResponse: OrderResponseDTO
    private var controller = OrderController()
    private var exceptionApplesResponse = OrderResponseDTO(
        message = "Number of Apples must be 0 or greater.",
        orderRequest = null,
        priceForAllApples = 0.0,
        priceForAllOranges = 0.0
    )
    private var exceptionNullInputResponse = OrderResponseDTO(
        message = "Received null Order request.",
        orderRequest = null,
        priceForAllApples = 0.0,
        priceForAllOranges = 0.0
    )
    private var exceptionOrangesResponse = OrderResponseDTO(
        message = "Number of Oranges must be 0 or greater.",
        orderRequest = null,
        priceForAllApples = 0.0,
        priceForAllOranges = 0.0
    )
    private var priceApple = 0.6
    private var priceOrange = 0.25

    @BeforeEach
    fun setUp() {
        mockRequest = OrderRequestDTO(
            numApples = 2,
            numOranges = 2
        )
        expectedResponse = OrderResponseDTO(
            message = "Order Summary",
            orderRequest = mockRequest,
            priceForAllApples = priceApple * mockRequest.numApples,
            priceForAllOranges = priceOrange * mockRequest.numOranges
        )
    }

    @Test
    fun submitOrder() {
        val testResponse = controller.submitOrder(mockRequest)
        assertEquals(expectedResponse, testResponse)
    }

    @Test
    fun submitOrder_negativeNumApples() {
        mockRequest.numApples = -1
        val testResponse = controller.submitOrder(mockRequest)
        assertEquals(exceptionApplesResponse, testResponse)
    }

    @Test
    fun submitOrder_negativeNumOranges() {
        mockRequest.numOranges = -1
        val testResponse = controller.submitOrder(mockRequest)
        assertEquals(exceptionOrangesResponse, testResponse)
    }

    @Test
    fun submitOrder_nullInput() {
        val testResponse = controller.submitOrder(null)
        assertEquals(exceptionNullInputResponse, testResponse)
    }
}