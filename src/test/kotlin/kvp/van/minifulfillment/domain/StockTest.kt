package kvp.van.minifulfillment.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class StockTest {
    @Test
    fun `재고를 생성한다`() {
        val sku = aSku()
        val center = aCenter()

        assertDoesNotThrow { Stock(center, sku) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3])
    internal fun `재고는 0보다 작을 수 없다`(quantity: Int) {
        assertThrows<IllegalArgumentException> {
            aStock(quantity = quantity)
        }
    }

    @Test
    internal fun `재고를 증가시킨다`() {
        val stock = aStock()

        stock.increase(1)

        assertThat(stock.quantity).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3])
    internal fun `음수로 재고를 증가시킬 수 없다`(quantity: Int) {
        val stock = aStock()

        assertThrows<IllegalArgumentException> {
            stock.increase(quantity)
        }
    }

    @Test
    internal fun `재고를 감소시킨다`() {
        val stock = aStock(quantity = 2)

        stock.decrease(-1)

        assertThat(stock.quantity).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    internal fun `양수로 재고를 감소시킬 수 없다`(quantity: Int) {
        val stock = aStock()

        assertThrows<IllegalArgumentException> {
            stock.decrease(quantity)
        }
    }

    @Test
    internal fun `재고 감소시 재고가 부족하다면 에러가 발생한다`() {
        val stock = aStock(quantity = 3)

        assertThrows<IllegalArgumentException> {
            stock.decrease(-5)
        }
    }
}

fun aStock(
    center: Center = aCenter(),
    sku: Sku = aSku(),
    quantity: Int = 0
) = Stock(center, sku, quantity)
