package kvp.van.minifulfillment.domain

private const val MAX_CODE_LENGTH = 20

class Barcode(
    val code: String,
    private val sku: Sku
) {
    init {
        require(code.isNotBlank()) { "바코드는 공백으로만 이루어질 수 없습니다." }
        require(code.length <= MAX_CODE_LENGTH) { "바코드는 ${MAX_CODE_LENGTH}자를 넘길 수 없습니다." }
    }
}
