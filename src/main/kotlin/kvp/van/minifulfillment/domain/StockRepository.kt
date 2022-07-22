package kvp.van.minifulfillment.domain

import org.springframework.data.jpa.repository.JpaRepository

fun StockRepository.getByCenterIdAndSkuId(centerId: Long, skuId: Long): Stock =
    findByCenter_IdAndSku_Id(centerId = centerId, skuId = skuId) ?: throw NoSuchElementException()

interface StockRepository : JpaRepository<Stock, Long> {
    fun findAllBySku(sku: Sku): List<Stock>

    fun findAllByCenter(center: Center): List<Stock>

    fun findByCenter_IdAndSku_Id(centerId: Long, skuId: Long): Stock?
}
