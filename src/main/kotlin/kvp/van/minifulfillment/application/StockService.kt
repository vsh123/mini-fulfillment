package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Center
import kvp.van.minifulfillment.domain.CenterRepository
import kvp.van.minifulfillment.domain.Sku
import kvp.van.minifulfillment.domain.SkuRepository
import kvp.van.minifulfillment.domain.Stock
import kvp.van.minifulfillment.domain.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StockService(
    private val centerRepository: CenterRepository,
    private val skuRepository: SkuRepository,
    private val stockRepository: StockRepository
) {
    fun create(sku: Sku): List<StockResponse> {
        val centers = centerRepository.findAll()

        val stocks = centers.map {
            Stock(it, sku)
        }

        return stockRepository.saveAll(stocks)
            .map(::StockResponse)
    }

    fun create(center: Center): List<StockResponse> {
        val skus = skuRepository.findAll()

        val stocks = skus.map {
            Stock(center, it)
        }
        return stockRepository.saveAll(stocks)
            .map(::StockResponse)
    }
}
