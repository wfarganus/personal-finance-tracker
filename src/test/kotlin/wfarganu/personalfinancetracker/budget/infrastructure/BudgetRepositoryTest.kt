package wfarganu.personalfinancetracker.budget.infrastructure

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import wfarganu.personalfinancetracker.InfrastructureTestBase
import wfarganu.personalfinancetracker.budget.domain.core.Budget
import wfarganu.personalfinancetracker.budget.domain.core.Spending
import wfarganu.personalfinancetracker.budget.domain.core.Spendings
import wfarganu.personalfinancetracker.shared.domain.Money
import java.math.BigDecimal
import java.time.Month

internal class BudgetRepositoryTest : InfrastructureTestBase() {

    @Autowired
    lateinit var budgetRepository: BudgetRepository

    @Test
    fun save() {
        // given
        val budget = Budget.create(
            year = 2023,
            month = Month.FEBRUARY,
            limit = Money(BigDecimal("1000.00")),
            spendings = Spendings(listOf(Spending("Groceries", Money(BigDecimal("100.00")))))
        )

        // when
        val savedBudget = budgetRepository.save(budget)

        // then
        assertNotNull(savedBudget)
    }

    @Test
    fun findByUuid() {
    }

    @Test
    fun deleteByUuid() {
    }

    @Test
    fun findAll() {
    }
}