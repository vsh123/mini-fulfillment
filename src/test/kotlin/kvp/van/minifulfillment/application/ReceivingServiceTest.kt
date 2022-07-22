package kvp.van.minifulfillment.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class ReceivingServiceTest {
    @Autowired
    private lateinit var skuService: SkuService

    @Autowired
    private lateinit var centerService: CenterService

    @Autowired
    private lateinit var receivingService: ReceivingService

    @Test
    internal fun `입고를 받아 재고를 증가시킨다`() {
        val sku = skuService.create(CreateSkuRequest(code = "code", name = "name"))
        val center = centerService.create(CreateCenterRequest(code = "code", name = "테스트센터"))

        val actual = receivingService.receive(
            ReceivingRequest(
                centerId = center.id,
                skuId = sku.id,
                quantity = 5
            )
        )

        assertThat(actual.quantity).isEqualTo(5)
    }

    @Test
    internal fun `입고수량은 0보다 커야한다`() {
        val sku = skuService.create(CreateSkuRequest(code = "code", name = "name"))
        val center = centerService.create(CreateCenterRequest(code = "code", name = "테스트센터"))

        assertThrows<IllegalArgumentException> {
            receivingService.receive(
                ReceivingRequest(
                    centerId = center.id,
                    skuId = sku.id,
                    quantity = 0
                )
            )
        }
    }
}
