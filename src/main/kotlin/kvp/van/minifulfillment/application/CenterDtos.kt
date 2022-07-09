package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Center

data class CreateCenterRequest(
    val code: String,
    val name: String
)

data class CenterResponse(
    val id: Long,
    val code: String,
    val name: String
) {
    constructor(center: Center) : this(center.id, center.code, center.name)
}
