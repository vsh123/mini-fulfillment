package kvp.van.minifulfillment.ui

import com.fasterxml.jackson.databind.ObjectMapper
import kvp.van.minifulfillment.application.CreateSkuRequest
import kvp.van.minifulfillment.application.SkuService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
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

    @MockBean
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
}
