package com.api_demo.apples_oranges_order_service.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class OrderTest {
    private lateinit var testSubject: Order

    @BeforeEach
    fun setUp() {
        testSubject = Order(
            id = "test-id",
            discountForApplesApplied = false,
            discountForOrangesApplied = false,
            numberOfApples = 4,
            numberOfOranges = 10,
            priceForAllApples = 2.4,
            priceForAllOranges = 2.5,
            priceTotal = 4.9
        )
    }

    @Test
    fun getId() {
        assertEquals("test-id", testSubject.id)
    }

    @Test
    fun setId() {
        testSubject.id = "test-id-2"
        assertEquals("test-id-2", testSubject.id)
    }

    @Test
    fun getDiscountForApplesApplied() {
        assertFalse(testSubject.discountForApplesApplied)
    }

    @Test
    fun setDiscountForApplesApplied() {
        testSubject.discountForApplesApplied = true
        assertTrue(testSubject.discountForApplesApplied)
    }

    @Test
    fun getDiscountForOrangesApplied() {
        assertFalse(testSubject.discountForOrangesApplied)
    }

    @Test
    fun setDiscountForOrangesApplied() {
        testSubject.discountForOrangesApplied = true
        assertTrue(testSubject.discountForOrangesApplied)
    }

    @Test
    fun getNumberOfApples() {
        assertEquals(4, testSubject.numberOfApples)
    }

    @Test
    fun setNumberOfApples() {
        testSubject.numberOfApples = 2
        assertEquals(2, testSubject.numberOfApples)
    }

    @Test
    fun getNumberOfOranges() {
        assertEquals(10, testSubject.numberOfOranges)
    }

    @Test
    fun setNumberOfOranges() {
        testSubject.numberOfOranges = 5
        assertEquals(5, testSubject.numberOfOranges)
    }

    @Test
    fun getPriceForAllApples() {
        assertEquals(2.4, testSubject.priceForAllApples)
    }

    @Test
    fun setPriceForAllApples() {
        testSubject.priceForAllApples = 1.5
        assertEquals(1.5, testSubject.priceForAllApples)
    }

    @Test
    fun getPriceForAllOranges() {
        assertEquals(2.5, testSubject.priceForAllOranges)
    }

    @Test
    fun setPriceForAllOranges() {
        testSubject.priceForAllOranges = 1.5
        assertEquals(1.5, testSubject.priceForAllOranges)
    }

    @Test
    fun getPriceTotal() {
        assertEquals(4.9, testSubject.priceTotal)
    }

    @Test
    fun setPriceTotal() {
        testSubject.priceTotal = 1.5
        assertEquals(1.5, testSubject.priceTotal)
    }
}