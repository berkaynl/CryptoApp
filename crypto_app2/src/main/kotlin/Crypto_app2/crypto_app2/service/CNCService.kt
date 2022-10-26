package Crypto_app2.crypto_app2.service

import Crypto_app2.crypto_app2.dto.cnc.ApiResult
import Crypto_app2.crypto_app2.dto.cnc.CurrencyInfo
import Crypto_app2.crypto_app2.model.CryptoCurrency
import Crypto_app2.crypto_app2.model.Market
import Crypto_app2.crypto_app2.repository.CryptoCurrencyRepository
import Crypto_app2.crypto_app2.repository.MarketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Instant
import java.util.*

@Service
class CNCService : IMarketUpdaterService {
    @Autowired
    private val restTemplate: RestTemplate? = null

    @Autowired
    private val cryptoCurrencyRepository: CryptoCurrencyRepository? = null

    @Autowired
    private val marketRepository: MarketRepository? = null
    private fun fetchFromAPI(): ApiResult? {
        val headers = HttpHeaders()
        headers[API_KEY_HEADER] = API_KEY
        val entity = HttpEntity("", headers)
        val response: ResponseEntity<ApiResult> ?= restTemplate?.exchange(
            URL, HttpMethod.GET, entity,
            ApiResult::class.java
        )
        return if (response?.statusCode!= HttpStatus.OK) null else response.body
    }

    private fun createFromDTO(currencyInfo: CurrencyInfo): CryptoCurrency {
        val cryptoCurrency = CryptoCurrency(currencyInfo.id, currencyInfo.name, currencyInfo.symbol)
        return cryptoCurrencyRepository!!.save(cryptoCurrency)
    }

    private fun createIfNotExists(currency: CurrencyInfo): CryptoCurrency {
        val externalId: Long? = currency.id
        val possibleCryptoCurrency: Optional<CryptoCurrency?>? = cryptoCurrencyRepository?.findOneByExternalId(externalId)
        return if (!possibleCryptoCurrency!!.isPresent()) {
            createFromDTO(currency)
        } else {
            possibleCryptoCurrency.get()
        }
    }

    override fun update() {
        val result: ApiResult = fetchFromAPI()?: return
        val timestamp: Instant? = result.status?.timestamp
        val fetchedCurrencies: Set<CurrencyInfo>? = result.data
        for (currencyInfo in fetchedCurrencies!!) {
            val currency: CryptoCurrency = createIfNotExists(currencyInfo)
            val market = Market(timestamp, currency, currencyInfo.quote?.usd?.price)
            marketRepository?.save(market)
        }
    }

    companion object {
        private const val API_KEY_HEADER = "X-CMC_PRO_API_KEY"
        private const val API_KEY = "Please provide a valid API key"
        private const val URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"
    }
}