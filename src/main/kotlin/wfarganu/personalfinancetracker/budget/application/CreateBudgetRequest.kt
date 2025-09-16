package wfarganu.personalfinancetracker.budget.application

import wfarganu.personalfinancetracker.shared.domain.Money
import java.time.Month

data class CreateBudgetRequest(val year: Int, val month: Month, val limit: Money)
