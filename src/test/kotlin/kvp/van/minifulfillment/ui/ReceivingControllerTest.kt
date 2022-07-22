package kvp.van.minifulfillment.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kvp.van.minifulfillment.application.ReceivingRequest
import kvp.van.minifulfillment.application.ReceivingService
import kvp.van.minifulfillment.application.StockResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(
    controllers = [ReceivingController::class]
)
internal class ReceivingControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var receivingService: ReceivingService

    @Test
    internal fun `입고수량 validation`() {
        mockMvc.post("/api/receivings") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(ReceivingRequest(centerId = 1, skuId = 1, quantity = 0))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `입고를 할 수 있다`() {
        val expected = StockResponse(centerId = 1, skuId = 1, quantity = 1)
        every { receivingService.receive(any()) } returns expected

        mockMvc.post("/api/receivings") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(ReceivingRequest(centerId = 1, skuId = 1, quantity = 1))
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(expected)) }
        }.andDo {
            print()
        }
    }
}
