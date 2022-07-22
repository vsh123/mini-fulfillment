package kvp.van.minifulfillment.ui

import kvp.van.minifulfillment.application.ReceivingRequest
import kvp.van.minifulfillment.application.ReceivingService
import kvp.van.minifulfillment.application.StockResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/receivings")
class ReceivingController(
    private val receivingService: ReceivingService
) {

    @PostMapping
    fun receive(@Valid @RequestBody request: ReceivingRequest): StockResponse {
        return receivingService.receive(request)
    }
}
