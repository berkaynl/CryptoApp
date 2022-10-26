package Crypto_app2.crypto_app2.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant
import javax.persistence.*

@Entity
class Market {
    @Id
    @GeneratedValue
    private val id: Long? = null

    @Column(nullable = false)
    var timestamp: Instant? = null
        private set

    @get:JsonIgnore
    @ManyToOne
    var cryptoCurrency: CryptoCurrency? = null
        private set

    @Column(nullable = false)
    var price: Double? = null
        private set

    constructor() {}
    constructor(timestamp: Instant?, cryptoCurrency: CryptoCurrency?, price: Double?) {
        this.timestamp = timestamp
        this.cryptoCurrency = cryptoCurrency
        this.price = price
    }
}
