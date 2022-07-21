package kvp.van.minifulfillment.application

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
}
