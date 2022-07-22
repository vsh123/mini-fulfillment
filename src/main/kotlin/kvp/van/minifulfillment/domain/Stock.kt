package kvp.van.minifulfillment.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Stock(
    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    val center: Center,

    @ManyToOne
    @JoinColumn(name = "sku_id", nullable = false)
    val sku: Sku,

    @Column(name = "`quantity`", nullable = false)
    var quantity: Int = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
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
