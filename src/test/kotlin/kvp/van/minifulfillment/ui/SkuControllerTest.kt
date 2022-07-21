package kvp.van.minifulfillment.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kvp.van.minifulfillment.application.CreateSkuRequest
import kvp.van.minifulfillment.application.SkuResponse
import kvp.van.minifulfillment.application.SkuService
import kvp.van.minifulfillment.domain.SkuStatus
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(
    controllers = [SkuController::class]
)
internal class SkuControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var skuService: SkuService

    @Test
    internal fun `코드 공백 입력 validation`() {
        mockMvc.post("/api/skus") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateSkuRequest(code = "              ", name = "name"))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `이름 공백 입력 validation`() {
        mockMvc.post("/api/skus") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateSkuRequest(code = "코드", name = "       "))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `이름 20자 초과 validation`() {
        mockMvc.post("/api/skus") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateSkuRequest(code = "코드", name = "a".repeat(21)))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `센터코드와 코드를 통해 신규 센터를 추가할 수 있다`() {
        val expected = SkuResponse(1L, "a".repeat(15), "a".repeat(15), SkuStatus.READY_LAUNCHING)
        every { skuService.create(any()) } returns expected
        mockMvc.post("/api/skus") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateSkuRequest(code = "a".repeat(15), name = "a".repeat(15)))
        }.andExpect {
            status { isCreated() }
            content { json(objectMapper.writeValueAsString(expected)) }
        }.andDo {
            print()
        }
    }
}
