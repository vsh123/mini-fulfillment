package kvp.van.minifulfillment.domain

private const val MAX_NAME_LENGTH = 20

class Sku(
    val code: String,
    val name: String,
    val status: SkuStatus = SkuStatus.READY_LAUNCHING,
    barcodes: Set<Barcode> = setOf()
) {
    private val _barcodes: MutableSet<Barcode> = barcodes.toMutableSet()
    val barcodes: Set<Barcode>
        get() = _barcodes.toSet()

    init {
        require(code.isNotBlank()) { "Sku코드는 공백으로만 이루어질 수 없습니다." }
        require(name.isNotBlank()) { "Sku이름은 공백으로만 이루어질 수 없습니다." }
        require(name.length <= MAX_NAME_LENGTH) { "Sku이름은 ${MAX_NAME_LENGTH}자를 넘길 수 없습니다." }
    }

    fun addBarcode(barcode: String) {
        _barcodes.add(Barcode(barcode, this))
    }
}

enum class SkuStatus(
    val description: String
) {
    READY_LAUNCHING("런칭 예정"),
    IN_SALE("판매중"),
    STOP_SALE("판매 중지")
}
