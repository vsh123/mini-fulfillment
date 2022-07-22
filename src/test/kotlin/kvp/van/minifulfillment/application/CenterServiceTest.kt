package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.CenterRepository
import kvp.van.minifulfillment.domain.SkuRepository
import kvp.van.minifulfillment.domain.StockRepository
import kvp.van.minifulfillment.domain.aSku
import kvp.van.minifulfillment.domain.get
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class CenterServiceTest {
    @Autowired
    private lateinit var skuRepository: SkuRepository

    @Autowired
    private lateinit var centerRepository: CenterRepository

    @Autowired
    private lateinit var stockRepository: StockRepository

    @Autowired
    private lateinit var centerService: CenterService

    @Test
    internal fun `센터를 생성한다`() {
        val request = CreateCenterRequest(code = "code", name = "테스트센터")
        val actual = centerService.create(request)

        assertAll(
            { Assertions.assertThat(actual.id).isNotEqualTo(0) },
            { Assertions.assertThat(actual.code).isEqualTo("code") },
            { Assertions.assertThat(actual.name).isEqualTo("테스트센터") }
        )
    }

    @Test
    internal fun `센터코드는 중복될 수 없다`() {
        val request = CreateCenterRequest(code = "code", name = "테스트센터")
        centerService.create(request)

        assertThrows<IllegalArgumentException>("동일한 코드로 생성 시 실패") {
            centerService.create(request)
        }
    }

    @Test
    internal fun `센터 생성 시 해당 센터의 모든 재고를 0으로 초기화한다`() {
        skuRepository.save(aSku())
        val request = CreateCenterRequest(code = "code", name = "테스트센터")
        val result = centerService.create(request)
        val center = centerRepository[result.id]

        val stocks = stockRepository.findAllByCenter(center)

        assertThat(stocks).hasSize(1)
        assertThat(stocks.all { it.quantity == 0 }).isTrue
    }
}
