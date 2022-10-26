package Crypto_app2.crypto_app2.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
class CryptoCurrency {
        @Id
        @GeneratedValue
        val id: Long? = null

        @Column(unique = true, nullable = false)
        var externalId: Long? = null
            private set

        @Column(nullable = false)
        var name: String? = null
            private set

        @Column(nullable = false)
        var symbol: String? = null
            private set

        @get:JsonIgnore
        @OneToMany(mappedBy = "field")
        val changes: Set<Market>? = null

        constructor() {}
        constructor(externalId: Long?, name: String?, symbol: String?) {
            this.externalId = externalId
            this.name = name
            this.symbol = symbol
        }
    }
