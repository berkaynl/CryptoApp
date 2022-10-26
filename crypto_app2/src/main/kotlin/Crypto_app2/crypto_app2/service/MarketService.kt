package Crypto_app2.crypto_app2.service

import Crypto_app2.crypto_app2.model.CryptoCurrency
import Crypto_app2.crypto_app2.model.Market
import Crypto_app2.crypto_app2.repository.CryptoCurrencyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

import kotlin.Comparator


/*@Service
class MarketService {
    @Autowired
    private val cryptoCurrencyRepository: CryptoCurrencyRepository? = null
    private fun findLatestMarket(changes: Set<Market?>): Market? {
        return changes.stream()
            .max(kotlin.Comparator.comparing<Market?, kotlin.Any>(Market::timestamp))
            .orElse(null)
    }

    fun getLatestMarketByCryptoCurrencyId(cryptoCurrencyId: Long): Market? {
        val optionalCryptoCurrency: Optional<CryptoCurrency?> = cryptoCurrencyRepository!!.findById(cryptoCurrencyId)
        if (optionalCryptoCurrency.isEmpty()) {
            return null
        }
        val cryptoCurrency: CryptoCurrency = optionalCryptoCurrency.get()
        val changes: Set<Market?> = cryptoCurrency.changes
        return findLatestMarket(changes)
    }
}*/

/*@Service
class MarketService {
    @Autowired
    private val cryptoCurrencyRepository: CryptoCurrencyRepository? = null
    private fun findLatestMarket(changes: Set<Market?>): Market? {
        return changes.stream()
            .max(kotlin.Comparator.comparing<Market?, kotlin.Any>(Market::timestamp))
            .orElse(null)
    }

    fun getLatestMarketByCryptoCurrencyId(cryptoCurrencyId: Long): Market? {
        val optionalCryptoCurrency: Optional<CryptoCurrency?> = cryptoCurrencyRepository!!.findById(cryptoCurrencyId)
        if (optionalCryptoCurrency.isEmpty()) {
            return null
        }
        val cryptoCurrency: CryptoCurrency = optionalCryptoCurrency.get()
        val changes: Set<Market?> = cryptoCurrency.changes
        return findLatestMarket(changes)
    }
}*/

@Service
class MarketService {
    @Autowired
    private val cryptoCurrencyRepository: CryptoCurrencyRepository? = null
    private fun findLatestMarket(changes: Set<Market>): Market? {
        return changes.stream()
            .max(Comparator.comparing(Market::timestamp))
            .orElse(null)
    }

    fun getLatestMarketByCryptoCurrencyId(cryptoCurrencyId: Long): Market? {
        val optionalCryptoCurrency = cryptoCurrencyRepository!!.findById(cryptoCurrencyId)
        if (optionalCryptoCurrency.isEmpty) {
            return null
        }
        val cryptoCurrency = optionalCryptoCurrency.get()
        val changes: Set<Market>? = cryptoCurrency.changes
        return changes?.let { findLatestMarket(it) }
    }
}