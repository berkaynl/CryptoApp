package Crypto_app2.crypto_app2.dto.cnc

import com.fasterxml.jackson.annotation.JsonProperty

class ConversionValueEntry {
    val price: Double? = null

    @JsonProperty("market_cap")
    val marketCap: Double? = null
}
