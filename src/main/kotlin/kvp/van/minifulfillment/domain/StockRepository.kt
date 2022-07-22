package kvp.van.minifulfillment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {
    fun findAllBySku(sku: Sku): List<Stock>

    fun findAllByCenter(center: Center): List<Stock>
}
