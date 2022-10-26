package Crypto_app2.crypto_app2.repository

import Crypto_app2.crypto_app2.model.CryptoCurrency
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CryptoCurrencyRepository : CrudRepository<CryptoCurrency?, Long?> {
    fun findOneByExternalId(externalId: Long?): Optional<CryptoCurrency?>?
    fun existsById(id: Long?): Boolean
}
