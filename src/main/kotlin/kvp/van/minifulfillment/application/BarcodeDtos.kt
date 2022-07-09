package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Barcode

data class CreateBarcodeRequest(
    val skuId: Long,
    val code: String
)

data class BarcodeResponse(
    val id: Long,
    val code: String
) {
    constructor(barcode: Barcode) : this(barcode.id, barcode.code)
}
