package kvp.van.minifulfillment.ui

import kvp.van.minifulfillment.application.BarcodeResponse
import kvp.van.minifulfillment.application.BarcodeService
import kvp.van.minifulfillment.application.CreateBarcodeRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/barcodes")
@RestController
class BarcodeController(
    private val barcodeService: BarcodeService
) {
    @PostMapping
    fun create(@Valid @RequestBody request: CreateBarcodeRequest): ResponseEntity<BarcodeResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(barcodeService.create(request))
    }
}
