package kvp.van.minifulfillment.application

import javax.validation.constraints.Min

data class ReceivingRequest(
    val skuId: Long,
    val centerId: Long,
    @field:Min(value = 1, message = "입고 수량은 1개 이상이여야 합니다.")
    val quantity: Int
)
