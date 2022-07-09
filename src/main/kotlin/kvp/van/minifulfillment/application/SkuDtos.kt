package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Sku
import kvp.van.minifulfillment.domain.SkuStatus

data class CreateSkuRequest(
    val code: String,
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
