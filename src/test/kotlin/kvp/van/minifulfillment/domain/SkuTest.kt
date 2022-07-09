package kvp.van.minifulfillment.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class SkuTest {

    @Test
    internal fun sku생성() {
        assertDoesNotThrow("SKU정상 생성") {
            aSku()
        }
    }

    @Test
    internal fun `SKU의 초기상태는 런칭예정이다`() {
        val sku = aSku()
        assertThat(sku.status).isEqualTo(SkuStatus.READY_LAUNCHING)
    }

    @ParameterizedTest
    @ValueSource(strings = ["         ", ""])
    internal fun `sku코드는 공백으로만 이루어질 수 없다`(code: String) {
        assertThrows<IllegalArgumentException> {
            aSku(code = code)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["         ", ""])
    internal fun `sku이름은 공백으로만 이루어질 수 없다`(name: String) {
        assertThrows<IllegalArgumentException> {
            aSku(name = name)
        }
    }

    @Test
    internal fun `sku이름은 최대 20자`() {
        assertDoesNotThrow("20자 이름 입력") {
            aSku(name = "a".repeat(20))
        }
        assertThrows<IllegalArgumentException>("21자 이름 입력") {
            aSku(name = "a".repeat(21))
        }
    }
}

fun aSku(code: String = "A0000001", name: String = "테스트SKU") = Sku(code = code, name = name)
