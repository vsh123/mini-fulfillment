package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.CenterRepository
import kvp.van.minifulfillment.domain.SkuRepository
import kvp.van.minifulfillment.domain.aCenter
import kvp.van.minifulfillment.domain.aSku
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class StockServiceTest {
    @Autowired
    private lateinit var skuRepository: SkuRepository

    @Autowired
    private lateinit var centerRepository: CenterRepository

    @Autowired
    private lateinit var stockService: StockService

    @Test
    internal fun `sku생성 시 모든 센터의 재고를 0으로 초기화한다`() {
        centerRepository.save(aCenter())
        val sku = skuRepository.save(aSku())

        val actual = stockService.create(sku)

        assertThat(actual).hasSize(1)
        assertThat(actual.all { it.quantity == 0 }).isTrue
    }

    @Test
    internal fun `center생성 시 해당 센터의 모든 재고를 0으로 초기화한다`() {
        skuRepository.save(aSku())
        val center = centerRepository.save(aCenter())

        val actual = stockService.create(center)

        assertThat(actual).hasSize(1)
        assertThat(actual.all { it.quantity == 0 }).isTrue
    }
}
