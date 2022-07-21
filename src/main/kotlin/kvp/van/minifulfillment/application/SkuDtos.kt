package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Sku
import kvp.van.minifulfillment.domain.SkuStatus
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateSkuRequest(
    @field:NotBlank(message = "Sku코드는 공백으로만 이루어질 수 없습니다.")
    val code: String,

    @field:Size(max = 20, message = "Sku이름은 최대 20자입니다.")
    @field:NotBlank(message = "Sku이름은 공백으로만 이루어질 수 없습니다.")
    val name: String
)

data class SkuResponse(
    val id: Long,
    val code: String,
    val name: String,
    val status: SkuStatus
) {
    constructor(sku: Sku) : this(id = sku.id, code = sku.code, name = sku.name, status = sku.status)
}
