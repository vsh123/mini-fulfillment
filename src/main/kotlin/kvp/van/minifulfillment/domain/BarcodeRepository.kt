package kvp.van.minifulfillment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BarcodeRepository : JpaRepository<Barcode, Long> {
    fun existsByCode(code: String): Boolean
}
