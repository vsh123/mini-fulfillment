package kvp.van.minifulfillment.domain

class Stock(
    val center: Center,
    val sku: Sku,
    var quantity: Int = 0
) {
    init {
        require(quantity >= 0) { "재고는 0보다 작을 수 없습니다." }
    }

    fun increase(increaseQuantity: Int) {
        require(increaseQuantity >= 0) { "증가시키려는 수량은 0이상이여야 합니다." }
        addQuantity(increaseQuantity)
    }

    fun decrease(decreaseQuantity: Int) {
        require(decreaseQuantity <= 0) { "감소시키려는 수량은 0이하여야 합니다." }
        addQuantity(decreaseQuantity)
    }

    private fun addQuantity(addedQuantity: Int) {
        quantity += addedQuantity
        require(quantity >= 0) { "마이너스 재고 발생" }
    }
}
