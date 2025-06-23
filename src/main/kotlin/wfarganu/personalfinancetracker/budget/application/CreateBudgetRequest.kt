package wfarganu.personalfinancetracker.budget.application

import wfarganu.personalfinancetracker.budget.domain.core.Money
import java.time.Month

data class CreateBudgetRequest(val year: Int, val month: Month, val limit: Money)
