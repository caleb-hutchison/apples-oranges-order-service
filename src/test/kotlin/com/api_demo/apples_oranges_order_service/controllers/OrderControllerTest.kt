package com.api_demo.apples_oranges_order_service.controllers

import com.api_demo.apples_oranges_order_service.models.DiscountRequestDTO
import com.api_demo.apples_oranges_order_service.models.Order
import com.api_demo.apples_oranges_order_service.models.OrderRequestDTO
import com.api_demo.apples_oranges_order_service.models.OrderResponseDTO
import com.api_demo.apples_oranges_order_service.services.OrderService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class OrderControllerTest {
    private lateinit var mockRequest: OrderRequestDTO
    private lateinit var expectedOrder: Order
    private lateinit var expectedResponse: OrderResponseDTO
    private lateinit var firstSubmittedOrder: Order

    private val mockOrderService: OrderService = mockk()
    private var controller = OrderController(mockOrderService)

    private var exceptionApplesResponse = OrderResponseDTO(
        message = "Number of Apples must be 0 or greater.",
        order = null
    )
    private var exceptionNullInputResponse = OrderResponseDTO(
        message = "Received null Order request.",
        order = null
    )
    private var exceptionOrangesResponse = OrderResponseDTO(
        message = "Number of Oranges must be 0 or greater.",
        order = null
    )

    @BeforeEach
    fun setUp() {
        mockRequest = OrderRequestDTO(
            numApples = 4,
            numOranges = 10
        )
        expectedOrder = Order(
            id = "test-id",
            discountForApplesApplied = false,
            discountForOrangesApplied = false,
            numberOfApples = 4,
            numberOfOranges = 10,
            priceForAllApples = 2.4,
            priceForAllOranges = 2.5,
            priceTotal = 4.9
        )
        expectedResponse = OrderResponseDTO(
            message = "Order Summary",
            order = expectedOrder
        )

        // Mock Service setup
        val expectedOrderWithNullId = expectedOrder.copy()
        expectedOrderWithNullId.id = null
        every { mockOrderService.getAllOrders() } returns
                listOf(expectedOrder)
        every { mockOrderService.getOrderById("test-id") } returns
                expectedOrder
        every { mockOrderService.save(expectedOrderWithNullId) } returns
                expectedOrder
    }

    @Test
    fun getAllOrders() {
        val testResponse = controller.getAllOrders()
        assertFalse(testResponse.isEmpty())
        assertTrue(testResponse.contains(expectedOrder))
    }

    @Test
    fun getOrderById() {
        val testResponse = controller.getOrderById(expectedOrder.id!!)
        assertEquals(expectedOrder, testResponse)
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

    @Test
    fun submitOrder_withDiscountsApplied() {
        val toggleDiscountRequest = DiscountRequestDTO(
            appleDiscountEnabled = true,
            orangeDiscountEnabled = true
        )
        controller.toggleDiscounts(toggleDiscountRequest)

        val expectedWithDiscounts = expectedResponse.copy()
        val expectedOrderWithDiscounts = Order(
            id = "test-id",
            discountForApplesApplied = true,
            discountForOrangesApplied = true,
            numberOfApples = 4,
            numberOfOranges = 10,
            priceForAllApples = 1.2,
            priceForAllOranges = 1.75,
            priceTotal = 2.95
        )
        val expectedOrderDiscountNullId = expectedOrderWithDiscounts.copy()
        expectedOrderDiscountNullId.id = null

        // Setup Mock Service
        every { mockOrderService.save(expectedOrderDiscountNullId) } returns
                expectedOrderWithDiscounts
        expectedWithDiscounts.order = expectedOrderWithDiscounts

        val testResponse = controller.submitOrder(mockRequest)
        assertEquals(expectedWithDiscounts, testResponse)
    }

    @Test
    fun toggleDiscounts_true() {
        val toggleDiscountRequest = DiscountRequestDTO(
            appleDiscountEnabled = true,
            orangeDiscountEnabled = true
        )
        val expected = """
                Apple Discount Enabled: true
                Orange Discount Enabled: true
            """.trimIndent()
        val testResponse = controller.toggleDiscounts(toggleDiscountRequest)
        assertEquals(expected, testResponse)
    }

    @Test
    fun toggleDiscounts_false() {
        val toggleDiscountRequest = DiscountRequestDTO(
            appleDiscountEnabled = false,
            orangeDiscountEnabled = false
        )
        val expected = """
                Apple Discount Enabled: false
                Orange Discount Enabled: false
            """.trimIndent()
        val testResponse = controller.toggleDiscounts(toggleDiscountRequest)
        assertEquals(expected, testResponse)
    }
}