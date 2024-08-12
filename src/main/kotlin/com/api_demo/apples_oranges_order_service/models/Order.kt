package com.api_demo.apples_oranges_order_service.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, String>

@Table("ORDERS")
data class Order(
    @Id
    @Column("ID")
    var id: String?,
    @Column("DISCOUNTFORAPPLESAPPLIED")
    var discountForApplesApplied: Boolean,
    @Column("DISCOUNTFORORANGESAPPLIED")
    var discountForOrangesApplied: Boolean,
    @Column("NUMBEROFAPPLES")
    var numberOfApples: Int,
    @Column("NUMBEROFORANGES")
    var numberOfOranges: Int,
    @Column("PRICEFORALLAPPLES")
    var priceForAllApples: Double,
    @Column("PRICEFORALLORANGES")
    var priceForAllOranges: Double,
    @Column("PRICETOTAL")
    var priceTotal: Double
)
