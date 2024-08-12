package com.api_demo.apples_oranges_order_service.services

import com.api_demo.apples_oranges_order_service.models.Order
import com.api_demo.apples_oranges_order_service.models.OrderRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.query
import java.util.*

class OrderServiceTest {
    private lateinit var expectedOrder: Order
    private lateinit var orderService: OrderService
    private val mockDb: OrderRepository = mockk()

    @BeforeEach
    fun setUp() {
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

        // Mock Setup
        every { mockDb.findAll() } returns listOf(expectedOrder)
        every { mockDb.findByIdOrNull(any<String>()) } returns expectedOrder
        every { mockDb.save(any<Order>()) } returns expectedOrder

        orderService = OrderService(mockDb)
    }

    @Test
    fun getAllOrders() {
        val testResult = orderService.getAllOrders()
        assertFalse(testResult.isEmpty())
        assertEquals(expectedOrder, testResult[0])
    }

    @Test
    fun getOrderById() {
        val testResult = orderService.getOrderById("test-id")
        assertEquals(expectedOrder, testResult)
    }

    @Test
    fun save() {
        val testResult = orderService.save(expectedOrder)
        assertEquals(expectedOrder, testResult)
    }
}
