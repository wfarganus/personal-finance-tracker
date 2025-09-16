package wfarganu.personalfinancetracker.budget.domain.core

import wfarganu.personalfinancetracker.ddd.ValueObject
import wfarganu.personalfinancetracker.shared.domain.Money
import java.math.BigDecimal

// by default params cannot be null unless explicitly specified with `?`
@ValueObject
data class Spending(val name: String, val amount: Money) {
    // Be careful with copy method in data classes when using init block
    // as the init is not called when copying the object, so the validation will not be performed
    init {
        require(name.isNotBlank()) { "Spending name cannot be blank" }
        require(amount.amount > BigDecimal.ZERO) { "Spending amount must be greater than zero" }
    }
}
