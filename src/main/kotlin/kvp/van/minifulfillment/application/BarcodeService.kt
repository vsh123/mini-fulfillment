package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.BarcodeRepository
import kvp.van.minifulfillment.domain.SkuRepository
import kvp.van.minifulfillment.domain.get
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class BarcodeService(
    private val skuRepository: SkuRepository,
    private val barcodeRepository: BarcodeRepository
) {
    fun create(request: CreateBarcodeRequest): BarcodeResponse {
        require(barcodeRepository.existsByCode(request.code).not()) { "이미 등록된 바코드가 존재합니다. ${request.code}" }
        val sku = skuRepository[request.skuId]
        val barcode = sku.addBarcode(request.code)
        barcodeRepository.save(barcode)
        return BarcodeResponse(barcode)
    }
}
