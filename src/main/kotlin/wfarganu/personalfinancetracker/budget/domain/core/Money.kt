package wfarganu.personalfinancetracker.budget.domain.core

import wfarganu.personalfinancetracker.ddd.ValueObject
import java.math.BigDecimal

@ValueObject
data class Money(val value: BigDecimal, val currency: String)
