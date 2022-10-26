package Crypto_app2.crypto_app2.controller

import Crypto_app2.crypto_app2.model.Market
import Crypto_app2.crypto_app2.repository.MarketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/market")
class MarketController {
    @Autowired
    private val marketRepository: MarketRepository? = null

    @get:GetMapping("/all")
    val marketPrices: ResponseEntity<Iterable<Market?>>
        get() = ResponseEntity.ok(marketRepository!!.findAll())
}


/*@RestController
@RequestMapping("/market")
@CrossOrigin
class CryptoCurrencyController {
    private val service: CryptoCurrencyService? = null
    @GetMapping("/list")
    fun list(): ResponseEntity<List<Market>> {
        val cryptoCurrencies: List<Market?> = service.all
        return if (CollectionUtils.isEmpty(cryptoCurrencies)) ResponseEntity.notFound()
            .build<List<Market>>() else ResponseEntity.ok<List<Market?>>(cryptoCurrencies)
    }
}*/

