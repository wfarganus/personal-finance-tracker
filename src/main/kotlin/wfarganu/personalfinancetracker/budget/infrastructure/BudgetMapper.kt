package wfarganu.personalfinancetracker.budget.infrastructure

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import wfarganu.personalfinancetracker.budget.domain.core.Budget
import wfarganu.personalfinancetracker.budget.domain.core.Spending
import wfarganu.personalfinancetracker.budget.domain.core.Spendings

@Mapper(componentModel = "spring")
internal interface BudgetMapper {

    @Mapping(source = "spendings", target = "spendings")
    fun toDomain(entity: BudgetEntity): Budget

    @Mapping(source = "spendings", target = "spendings")
    fun toEntity(domain: Budget): BudgetEntity

    fun toDomain(spending: SpendingEntity): Spending
    fun toEntity(spending: Spending): SpendingEntity

    fun toDomain(spendings: List<SpendingEntity>): Spendings = Spendings(spendings.map { toDomain(it) })

    fun toEntity(spendings: Spendings): MutableList<SpendingEntity> =
        spendings.spendings.map { toEntity(it) }.toMutableList()
}