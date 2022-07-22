package kvp.van.minifulfillment.application

import kvp.van.minifulfillment.domain.Center
import kvp.van.minifulfillment.domain.CenterRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CenterService(
    private val stockService: StockService,
    private val centerRepository: CenterRepository
) {
    fun create(request: CreateCenterRequest): CenterResponse {
        require(centerRepository.existsByCode(request.code).not()) { "이미 등록된 센터코드입니다. ${request.code}" }
        val center = centerRepository.save(
            Center(
                code = request.code,
                name = request.name
            )
        )
        stockService.create(center)

        return CenterResponse(center)
    }
}
