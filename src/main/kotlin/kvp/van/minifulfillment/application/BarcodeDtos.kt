package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Barcode
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateBarcodeRequest(
    val skuId: Long,

    @field:Size(max = 20, message = "바코드는 최대 20자입니다.")
    @field:NotBlank(message = "바코드는 공백으로만 이루어질 수 없습니다.")
    val code: String
)

data class BarcodeResponse(
    val id: Long,
    val code: String
) {
    constructor(barcode: Barcode) : this(barcode.id, barcode.code)
}
