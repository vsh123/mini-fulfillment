package kvp.van.minifulfillment.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

private const val MAX_CODE_LENGTH = 20

@Entity
class Barcode(
    @Column(name = "`code`", length = MAX_CODE_LENGTH, unique = true, nullable = false)
    val code: String,

    @ManyToOne
    @JoinColumn(name = "sku_id", nullable = false)
    private val sku: Sku,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    init {
        require(code.isNotBlank()) { "바코드는 공백으로만 이루어질 수 없습니다." }
        require(code.length <= MAX_CODE_LENGTH) { "바코드는 ${MAX_CODE_LENGTH}자를 넘길 수 없습니다." }
    }
}
