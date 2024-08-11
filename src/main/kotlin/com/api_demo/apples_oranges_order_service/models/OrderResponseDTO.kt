package com.api_demo.apples_oranges_order_service.models

data class OrderResponseDTO(
    var message: String?,
    var orderRequest: OrderRequestDTO?,
    var priceForAllApples: Double,
    var priceForAllOranges: Double
) {
    init {
        if (orderRequest != null) {
            require(orderRequest?.numApples!! >= 0) {
                "Number of Apples must be 0 or greater."
            }
            require(orderRequest?.numOranges!! >= 0) {
                "Number of Oranges must be 0 or greater."
            }
        }
    }

    var priceTotal: Double = priceForAllApples + priceForAllOranges
}
