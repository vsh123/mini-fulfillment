package kvp.van.minifulfillment.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BarcodeTest {
    @Test
    internal fun `바코드를 등록한다`() {
        val sku = aSku()
        assertDoesNotThrow {
            Barcode("barcode", sku)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["         ", ""])
    internal fun `바코드는 공백으로만 이루어질 수 없다`(code: String) {
        assertThrows<IllegalArgumentException> {
            aBarcode(code = code)
        }
    }

    @Test
    internal fun `바코드는 최대 20자`() {
        assertDoesNotThrow("20자 코드 입력") {
            aBarcode(code = "a".repeat(20))
        }
        assertThrows<IllegalArgumentException>("21자 코드 입력") {
            aBarcode(code = "a".repeat(21))
        }
    }
}

fun aBarcode(code: String = "barcode", sku: Sku = aSku()) = Barcode(code, sku)
