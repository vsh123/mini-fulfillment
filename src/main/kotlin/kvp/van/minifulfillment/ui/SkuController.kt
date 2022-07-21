package kvp.van.minifulfillment.ui

import kvp.van.minifulfillment.application.CreateSkuRequest
import kvp.van.minifulfillment.application.SkuResponse
import kvp.van.minifulfillment.application.SkuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/skus")
@RestController
class SkuController(
    private val skuService: SkuService
) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateSkuRequest): ResponseEntity<SkuResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(skuService.create(request))
    }
}
