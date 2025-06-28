package wfarganu.personalfinancetracker.budget.infrastructure

import wfarganu.personalfinancetracker.budget.domain.core.Budget
import wfarganu.personalfinancetracker.budget.domain.core.Spending
import wfarganu.personalfinancetracker.budget.domain.core.Spendings

internal object BudgetMapper {

    fun toDomain(entity: BudgetEntity): Budget = Budget.recreate(
        uuid = entity.uuid,
        year = entity.year,
        month = entity.month.let { it ?: throw IllegalArgumentException("Month cannot be null") },
        limit = entity.limit,
        locked = entity.locked,
        spendings = Spendings(entity.spendings.map { Spending(it.name, it.amount) })
    )

    fun toEntity(domain: Budget): BudgetEntity = BudgetEntity(
        uuid = domain.uuid,
        year = domain.year,
        month = domain.month,
        limit = domain.limit,
        locked = domain.locked,
        spendings = domain.spendings.map { SpendingEntity(name = it.name, amount = it.amount) }.toMutableList()
    )
}