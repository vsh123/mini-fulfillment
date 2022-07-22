package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.CenterRepository
import kvp.van.minifulfillment.domain.SkuRepository
import kvp.van.minifulfillment.domain.StockRepository
import kvp.van.minifulfillment.domain.aCenter
import kvp.van.minifulfillment.domain.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class SkuServiceTest {
    @Autowired
    private lateinit var skuRepository: SkuRepository

    @Autowired
    private lateinit var centerRepository: CenterRepository

    @Autowired
    private lateinit var stockRepository: StockRepository

    @Autowired
    private lateinit var skuService: SkuService

    @Test
    internal fun `sku를 생성한다`() {
        val request = CreateSkuRequest(code = "code", name = "name")

        val actual = skuService.create(request)

        assertAll(
            { assertThat(actual.id).isNotEqualTo(0) },
            { assertThat(actual.code).isEqualTo("code") },
            { assertThat(actual.name).isEqualTo("name") }
        )
    }

    @Test
    internal fun `sku코드는 중복될 수 없다`() {
        val request = CreateSkuRequest(code = "code", name = "name")
        skuService.create(request)

        assertThrows<IllegalArgumentException>("동일한 코드로 생성 시 실패") {
            skuService.create(request)
        }
    }

    @Test
    internal fun `sku생성 시 모든 센터의 해당 재고를 0으로 생성한다`() {
        centerRepository.save(aCenter())
        val request = CreateSkuRequest(code = "code", name = "name")
        val result = skuService.create(request)
        val sku = skuRepository[result.id]

        val stocks = stockRepository.findAllBySku(sku)

        assertThat(stocks).hasSize(1)
        assertThat(stocks.all { it.quantity == 0 }).isTrue
    }
}
