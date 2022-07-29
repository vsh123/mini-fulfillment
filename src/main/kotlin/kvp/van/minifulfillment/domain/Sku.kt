package kvp.van.minifulfillment.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

private const val MAX_NAME_LENGTH = 20

@Entity
class Sku(
    @Column(name = "`code`", length = 20, unique = true, nullable = false)
    val code: String,

    @Column(name = "`name`", length = MAX_NAME_LENGTH, nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`", length = 20, nullable = false)
    val status: SkuStatus = SkuStatus.READY_LAUNCHING,

    barcodes: Set<Barcode> = setOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    val canReceive: Boolean
        get() = RECEIVABLE_STATUSES.contains(status)

    @OneToMany(mappedBy = "sku")
    private val _barcodes: MutableSet<Barcode> = barcodes.toMutableSet()
    val barcodes: Set<Barcode>
        get() = _barcodes.toSet()

    init {
        require(code.isNotBlank()) { "Sku코드는 공백으로만 이루어질 수 없습니다." }
        require(name.isNotBlank()) { "Sku이름은 공백으로만 이루어질 수 없습니다." }
        require(name.length <= MAX_NAME_LENGTH) { "Sku이름은 ${MAX_NAME_LENGTH}자를 넘길 수 없습니다." }
    }

    fun addBarcode(code: String): Barcode {
        val barcode = Barcode(code, this)
        _barcodes.add(barcode)
        return barcode
    }
}

private val RECEIVABLE_STATUSES: List<SkuStatus> = listOf(SkuStatus.READY_LAUNCHING, SkuStatus.IN_SALE)

enum class SkuStatus(
    val description: String
) {
    READY_LAUNCHING("런칭 예정"),
    IN_SALE("판매중"),
    STOP_SALE("판매 중지")
}
