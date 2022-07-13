package kvp.van.minifulfillment.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kvp.van.minifulfillment.application.CenterResponse
import kvp.van.minifulfillment.application.CenterService
import kvp.van.minifulfillment.application.CreateCenterRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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

    @MockkBean
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

    @Test
    internal fun `센터코드와 코드를 통해 신규 센터를 추가할 수 있다`() {
        val expected = CenterResponse(1L, "a".repeat(15), "a".repeat(15))
        every { centerService.create(any()) } returns expected
        mockMvc.post("/api/centers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateCenterRequest(code = "a".repeat(15), name = "a".repeat(15)))
        }.andExpect {
            status { isCreated() }
            content { json(objectMapper.writeValueAsString(expected)) }
        }.andDo {
            print()
        }
    }
}
