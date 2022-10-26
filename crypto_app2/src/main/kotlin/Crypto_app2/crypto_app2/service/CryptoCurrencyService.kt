package Crypto_app2.crypto_app2.service

import Crypto_app2.crypto_app2.model.CryptoCurrency
import Crypto_app2.crypto_app2.model.Market
import Crypto_app2.crypto_app2.repository.CryptoCurrencyRepository
import Crypto_app2.crypto_app2.repository.MarketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class CryptoCurrencyService {
    @Autowired
    private val cryptoCurrencyRepository: CryptoCurrencyRepository? = null

    @Autowired
    private val marketRepository: MarketRepository? = null
    val all: Iterable<CryptoCurrency?>
        get() = cryptoCurrencyRepository!!.findAll()

    fun getById(id: Long): CryptoCurrency? {
        val cryptoCurrency = cryptoCurrencyRepository!!.findById(id)
        return cryptoCurrency.orElse(null)
    }

    fun getAllChangesById(id: Long): Iterable<Market?>? {
        return if (!cryptoCurrencyRepository!!.existsById(id)) null else marketRepository!!.findAllByCryptoCurrencyId(id)
    }
}
