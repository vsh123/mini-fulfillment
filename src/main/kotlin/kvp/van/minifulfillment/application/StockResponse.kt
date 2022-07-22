package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Stock

data class StockResponse(
    val centerId: Long,
    val skuId: Long,
    val quantity: Int
) {
    constructor(stock: Stock) : this(centerId = stock.center.id, skuId = stock.sku.id, stock.quantity)
}
