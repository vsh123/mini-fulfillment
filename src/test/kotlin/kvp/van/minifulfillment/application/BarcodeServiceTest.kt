package kvp.van.minifulfillment.application

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class BarcodeServiceTest {
    @Autowired
    private lateinit var barcodeService: BarcodeService

    @Autowired
    private lateinit var skuService: SkuService

    @Test
    internal fun `바코드 생성`() {
        val createSkuRequest = CreateSkuRequest(code = "code", name = "name")
        val sku = skuService.create(createSkuRequest)
        val createBarcodeRequest = CreateBarcodeRequest(sku.id, "바코드")
        val actual = barcodeService.create(createBarcodeRequest)

        assertAll(
            { Assertions.assertThat(actual.id).isNotEqualTo(0) },
            { Assertions.assertThat(actual.code).isEqualTo("바코드") }
        )
    }
}
