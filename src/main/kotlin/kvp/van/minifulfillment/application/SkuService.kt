package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Sku
import kvp.van.minifulfillment.domain.SkuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class SkuService(
    private val skuRepository: SkuRepository
) {
    fun create(request: CreateSkuRequest): SkuResponse {
        require(skuRepository.existsByCode(request.code).not()) { "이미 동일한 Sku코드가 존재합니다. ${request.code}" }

        val sku = skuRepository.save(
            Sku(
                code = request.code,
                name = request.name
            )
        )
        return SkuResponse(sku)
    }
}
