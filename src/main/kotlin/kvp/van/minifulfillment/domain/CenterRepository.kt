package kvp.van.minifulfillment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CenterRepository : JpaRepository<Center, Long> {
    fun existsByCode(code: String): Boolean
}
