package kvp.van.minifulfillment.ui

import com.fasterxml.jackson.databind.ObjectMapper
import kvp.van.minifulfillment.application.CenterService
import kvp.van.minifulfillment.application.CreateCenterRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(
    controllers = [CenterController::class]
)
internal class CenterControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var centerService: CenterService

    @Test
    internal fun `코드 공백 입력 validation`() {
        mockMvc.post("/api/centers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateCenterRequest(code = "              ", name = "name"))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `이름 공백 입력 validation`() {
        mockMvc.post("/api/centers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateCenterRequest(code = "코드", name = "       "))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `이름 20자 초과 validation`() {
        mockMvc.post("/api/centers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateCenterRequest(code = "코드", name = "a".repeat(21)))
        }.andExpect {
            status { isBadRequest() }
        }.andDo {
            print()
        }
    }
}
