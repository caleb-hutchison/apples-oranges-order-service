package com.api_demo.apples_oranges_order_service.models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DiscountRequestDTOTest {
    private lateinit var testSubject: DiscountRequestDTO

    @BeforeEach
    fun setUp() {
        testSubject = DiscountRequestDTO(
            appleDiscountEnabled = true,
            orangeDiscountEnabled = true
        )
    }

    @Test
    fun getAppleDiscountEnabled() {
        assertTrue(testSubject.appleDiscountEnabled)
    }

    @Test
    fun setAppleDiscountEnabled() {
        testSubject.appleDiscountEnabled = false
        assertFalse(testSubject.appleDiscountEnabled)
    }

    @Test
    fun getOrangeDiscountEnabled() {
        assertTrue(testSubject.orangeDiscountEnabled)
    }

    @Test
    fun setOrangeDiscountEnabled() {
        testSubject.orangeDiscountEnabled = false
        assertFalse(testSubject.orangeDiscountEnabled)
    }
}