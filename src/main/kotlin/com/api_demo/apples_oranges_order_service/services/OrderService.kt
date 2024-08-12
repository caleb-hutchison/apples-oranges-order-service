package com.api_demo.apples_oranges_order_service.services

import com.api_demo.apples_oranges_order_service.models.Order
import com.api_demo.apples_oranges_order_service.models.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(val db: OrderRepository) {
    fun getAllOrders(): List<Order> = db.findAll().toList()

    fun getOrderById(id: String): Order? = db.findByIdOrNull(id)

    fun save(order: Order): Order {
        db.save(order)
        return order
    }
}