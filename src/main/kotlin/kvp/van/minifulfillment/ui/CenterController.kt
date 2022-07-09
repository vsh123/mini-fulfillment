package kvp.van.minifulfillment.ui

import kvp.van.minifulfillment.application.CenterResponse
import kvp.van.minifulfillment.application.CenterService
import kvp.van.minifulfillment.application.CreateCenterRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/centers")
@RestController
class CenterController(
    private val centerService: CenterService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: CreateCenterRequest): ResponseEntity<CenterResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(centerService.create(request))
    }
}
