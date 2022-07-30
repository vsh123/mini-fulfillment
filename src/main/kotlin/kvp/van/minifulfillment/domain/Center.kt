package kvp.van.minifulfillment.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

private const val MAX_NAME_LENGTH = 20

@Entity
class Center(
    @Column(name = "`code`", length = 20, unique = true, nullable = false)
    val code: String,

    @Column(name = "`name`", length = MAX_NAME_LENGTH, nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "`status`", length = 20, nullable = false)
    val status: CenterStatus = CenterStatus.READY_OPEN,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    val canReceive: Boolean
        get() = RECEIVABLE_STATUSES.contains(status)

    init {
        require(code.isNotBlank()) { "센터코드는 공백으로만 이루어질 수 없습니다." }
        require(name.isNotBlank()) { "센터이름은 공백으로만 이루어질 수 없습니다." }
        require(name.length <= MAX_NAME_LENGTH) { "센터이름은 ${MAX_NAME_LENGTH}자를 넘길 수 없습니다." }
    }
}

private val RECEIVABLE_STATUSES: List<CenterStatus> = listOf(CenterStatus.READY_OPEN, CenterStatus.OPENED)

enum class CenterStatus(
    val description: String
) {
    READY_OPEN("오픈 준비 중"),
    OPENED("운영 중"),
    CLOSED("폐점"),
}
