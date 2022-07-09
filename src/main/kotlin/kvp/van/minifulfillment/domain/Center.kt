package kvp.van.minifulfillment.domain

private const val MAX_NAME_LENGTH = 20

class Center(
    val code: String,
    val name: String,
    val status: CenterStatus = CenterStatus.READY_OPEN
) {
    init {
        require(code.isNotBlank()) { "센터코드는 공백으로만 이루어질 수 없습니다." }
        require(name.isNotBlank()) { "센터이름은 공백으로만 이루어질 수 없습니다." }
        require(name.length <= MAX_NAME_LENGTH) { "센터이름은 ${MAX_NAME_LENGTH}자를 넘길 수 없습니다." }
    }
}

enum class CenterStatus(
    val description: String
) {
    READY_OPEN("오픈 준비 중"),
    OPENED("운영 중"),
    CLOSED("운영 중"),
}
