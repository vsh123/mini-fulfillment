package kvp.van.minifulfillment.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

operator fun CenterRepository.get(id: Long): Center =
    findByIdOrNull(id) ?: throw NoSuchElementException("${id}에 해당하는 sku가 없습니다.")

interface CenterRepository : JpaRepository<Center, Long> {
    fun existsByCode(code: String): Boolean
}
