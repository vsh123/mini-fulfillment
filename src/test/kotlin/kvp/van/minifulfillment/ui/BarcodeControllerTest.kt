package kvp.van.minifulfillment.ui

import com.fasterxml.jackson.databind.ObjectMapper
import kvp.van.minifulfillment.application.BarcodeService
import kvp.van.minifulfillment.application.CreateBarcodeRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(
    controllers = [BarcodeController::class]
)
internal class BarcodeControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var barcodeService: BarcodeService

    @Test
    internal fun `코드 공백 입력 validation`() {
        mockMvc.post("/api/barcodes") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateBarcodeRequest(skuId = 1, code = "              "))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `코드 20자 초과 validation`() {
        mockMvc.post("/api/barcodes") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateBarcodeRequest(skuId = 1, code = "a".repeat(21)))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }
}
