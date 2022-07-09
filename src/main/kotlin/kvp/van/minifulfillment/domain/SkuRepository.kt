package kvp.van.minifulfillment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SkuRepository : JpaRepository<Sku, Long>
