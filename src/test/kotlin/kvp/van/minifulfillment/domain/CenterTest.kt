package kvp.van.minifulfillment.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CenterTest {
    @Test
    internal fun `센터 정상 생성`() {
        assertDoesNotThrow("Center정상 생성") {
            Center(code = "code", name = "테스트 센터")
        }
    }

    @Test
    internal fun `센터의 초기상태는 오픈 준비 중이다`() {
        val center = aCenter()

        assertThat(center.status).isEqualTo(CenterStatus.READY_OPEN)
    }

    @ParameterizedTest
    @ValueSource(strings = ["         ", ""])
    internal fun `센터코드는 공백으로만 이루어질 수 없다`(code: String) {
        assertThrows<IllegalArgumentException> {
            aCenter(code = code)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["         ", ""])
    internal fun `센터이름은 공백으로만 이루어질 수 없다`(name: String) {
        assertThrows<IllegalArgumentException> {
            aCenter(name = name)
        }
    }

    @Test
    internal fun `센터이름은 최대 20자`() {
        assertDoesNotThrow("20자 이름 입력") {
            aCenter(name = "a".repeat(20))
        }
        assertThrows<IllegalArgumentException>("21자 이름 입력") {
            aCenter(name = "a".repeat(21))
        }
    }
}

fun aCenter(code: String = "code", name: String = "테스트 센터", status: CenterStatus = CenterStatus.READY_OPEN) =
    Center(code = code, name = name, status = status)
