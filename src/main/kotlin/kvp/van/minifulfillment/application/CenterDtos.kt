package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Center
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateCenterRequest(
    @field:NotBlank(message = "센터코드는 공백으로만 이루어질 수 없습니다.")
    val code: String,

    @field:Size(max = 20, message = "센터이름은 최대 20자입니다.")
    @field:NotBlank(message = "센터이름은 공백으로만 이루어질 수 없습니다.")
    val name: String
)

data class CenterResponse(
    val id: Long,
    val code: String,
    val name: String
) {
    constructor(center: Center) : this(center.id, center.code, center.name)
}
