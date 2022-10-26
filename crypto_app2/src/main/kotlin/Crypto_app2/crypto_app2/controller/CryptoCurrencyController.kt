package Crypto_app2.crypto_app2.controller

import Crypto_app2.crypto_app2.model.CryptoCurrency
import Crypto_app2.crypto_app2.model.Market
import Crypto_app2.crypto_app2.service.CryptoCurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/crypto")
class CryptoCurrencyController {

    @Autowired
    private val cryptoCurrencyService: CryptoCurrencyService? = null

    @get:GetMapping("/all")
    val allCryptoCurrencies: ResponseEntity<Iterable<Any?>?>?
        get() = ResponseEntity.ok(cryptoCurrencyService?.all)

    @GetMapping("/{id}")
    fun getCryptoCurrencyById(@PathVariable id: Long): ResponseEntity<CryptoCurrency?>? {
        val cryptoCurrency: CryptoCurrency = cryptoCurrencyService?.getById(id)
            ?: return ResponseEntity.notFound().build<CryptoCurrency?>()
        return ResponseEntity.ok<CryptoCurrency?>(cryptoCurrency)
    }

    @GetMapping("/{id}/market")
    fun getChangesByCryptoCurrencyId(@PathVariable id: Long): ResponseEntity<out Iterable<Market?>> {
        val changes: Iterable<Market?> = cryptoCurrencyService?.getAllChangesById(id)
            ?: return ResponseEntity.notFound().build<Iterable<Market>?>()
        return ResponseEntity.ok<Iterable<Market?>?>(changes)
    }
}