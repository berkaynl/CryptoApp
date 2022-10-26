package Crypto_app2.crypto_app2.repository

import Crypto_app2.crypto_app2.model.Market
import org.springframework.data.repository.CrudRepository

interface MarketRepository : CrudRepository<Market?, Long?> {
    fun findAllByCryptoCurrencyId(id: Long?): Iterable<Market?>?
}
