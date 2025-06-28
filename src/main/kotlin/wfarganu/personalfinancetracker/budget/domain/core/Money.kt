package wfarganu.personalfinancetracker.budget.domain.core

import wfarganu.personalfinancetracker.ddd.ValueObject
import java.math.BigDecimal

const val DEFAULT_CURRENCY = "PLN"
@ValueObject
data class Money(val amount: BigDecimal, val currency: String = DEFAULT_CURRENCY) {
    init {
        require(amount >= BigDecimal.ZERO) { "Money value cannot be negative" }
        require(currency.isNotBlank()) { "Currency cannot be blank" }
    }

    operator fun plus(other: Money): Money {
        require(currency == other.currency) { "Cannot add money with different currencies" }
        return Money(amount + other.amount, currency)
    }

    companion object {
        val ZERO: Money
            get() = Money(BigDecimal.ZERO)
    }
}
