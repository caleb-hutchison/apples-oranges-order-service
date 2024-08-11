package com.api_demo.apples_oranges_order_service.models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class OrderRequestDTOTest {
    private lateinit var testSubject: OrderRequestDTO

    @BeforeEach
    fun setUp() {
        testSubject = OrderRequestDTO(
            numApples = 1,
            numOranges = 1
        )
    }

    @Test
    fun getNumApples() {
        assertEquals(1, testSubject.numApples)
    }

    @Test
    fun setNumApples() {
        testSubject.numApples = 2
        assertEquals(2, testSubject.numApples)
    }

    @Test
    fun getNumOranges() {
        assertEquals(1, testSubject.numOranges)
    }

    @Test
    fun setNumOranges() {
        testSubject.numOranges = 2
        assertEquals(2, testSubject.numOranges)
    }
}