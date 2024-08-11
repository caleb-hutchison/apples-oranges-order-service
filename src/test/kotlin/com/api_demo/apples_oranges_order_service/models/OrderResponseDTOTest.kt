package com.api_demo.apples_oranges_order_service.models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

class OrderResponseDTOTest {
    private lateinit var testRequest: OrderRequestDTO
    private lateinit var testResponse: OrderResponseDTO
    private var priceApple = 0.6
    private var priceOrange = 0.25
    private var testMessage = "test message"

    @BeforeEach
    fun setUp() {
        testRequest = OrderRequestDTO(
            numApples = 1,
            numOranges = 1
        )
        testResponse = OrderResponseDTO(
            message = testMessage,
            orderRequest = testRequest,
            priceForAllApples = priceApple * testRequest.numApples,
            priceForAllOranges = priceOrange * testRequest.numOranges
        )
    }

    @Test
    fun getMessage() {
        assertEquals(testMessage, testResponse.message)
    }

    @Test
    fun getOrderRequest() {
        assertEquals(testRequest, testResponse.orderRequest)
    }

    @Test
    fun getPriceForAllApples() {
        assertEquals(
            priceApple * testRequest.numApples,
            testResponse.priceForAllApples
        )
    }

    @Test
    fun getPriceForAllOranges() {
        assertEquals(
            priceOrange * testRequest.numOranges,
            testResponse.priceForAllOranges
        )
    }

    @Test
    fun getPriceTotal() {
        assertEquals(
            testResponse.priceForAllApples + testResponse.priceForAllOranges,
            testResponse.priceTotal
        )
    }

    @Test
    fun negativeNumApples_throwsException() {
        var exception = assertFailsWith<IllegalArgumentException>(
            block = {
                testRequest = OrderRequestDTO(-1, 0)
                testResponse = OrderResponseDTO(
                    message = testMessage,
                    orderRequest = testRequest,
                    priceForAllApples = 1.0,
                    priceForAllOranges = 1.0
                )
            }
        )
        assertEquals(
            "Number of Apples must be 0 or greater.",
            exception.message
        )
        exception = assertFailsWith<IllegalArgumentException>(
            block = {
                testRequest = OrderRequestDTO(-1, -1)
                testResponse = OrderResponseDTO(
                    message = testMessage,
                    orderRequest = testRequest,
                    priceForAllApples = 1.0,
                    priceForAllOranges = 1.0
                )
            }
        )
        assertEquals(
            "Number of Apples must be 0 or greater.",
            exception.message
        )
    }

    @Test
    fun negativeNumOranges_throwsException() {
        val exception = assertFailsWith<IllegalArgumentException>(
            block = {
                testRequest = OrderRequestDTO(0, -1)
                testResponse = OrderResponseDTO(
                    message = testMessage,
                    orderRequest = testRequest,
                    priceForAllApples = 1.0,
                    priceForAllOranges = 1.0
                )
            }
        )
        assertEquals(
            "Number of Oranges must be 0 or greater.",
            exception.message
        )
    }
}