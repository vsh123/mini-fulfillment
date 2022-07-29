package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.StockRepository
import kvp.van.minifulfillment.domain.getByCenterIdAndSkuId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ReceivingService(
    private val stockRepository: StockRepository
) {
    fun receive(request: ReceivingRequest): StockResponse {
        require(request.quantity > 0) { "입고수량은 0보다 커야합니다" }
        val stock = stockRepository.getByCenterIdAndSkuId(centerId = request.centerId, skuId = request.skuId)
        stock.receive(request.quantity)
        return StockResponse(stock)
    }
}
