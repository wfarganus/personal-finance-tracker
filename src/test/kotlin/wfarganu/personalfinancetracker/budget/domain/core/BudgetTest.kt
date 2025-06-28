package wfarganu.personalfinancetracker.budget.domain.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.time.Month

internal class BudgetTest {

    /**
     * As the user I want to create a budget for a specific month
     */
    @Test
    fun shouldCreateBudget() {
        // given
        val year = 2025
        val month = Month.FEBRUARY
        val limit = Money(BigDecimal("100.00"), "PLN")

        // when
        val budget = Budget.create(year, month, limit)

        // then
        assertNotNull(budget)
        assertEquals(year, budget.year)
        assertEquals(month, budget.month)
        assertEquals(limit, budget.limit)
    }

    /**
     * As the user I want to change the limit of my budget
     */
    @Test
    fun shouldSuccessfullyChangeLimit() {
        // given
        val newLimit = Money(BigDecimal("2000.00"), "PLN")
        val budget = Budget.create(2025, Month.FEBRUARY, Money(BigDecimal("100.00"), "PLN"))

        // when
        val changedBudget = budget.changeLimit(newLimit)

        // then
        assertTrue(changedBudget.isSuccess)
        // We can use getOrThrow() to retrieve the value from Result if we are sure it is successful
        assertEquals(budget.uuid, changedBudget.getOrThrow().uuid)
        assertTrue(newLimit == changedBudget.getOrThrow().limit)
    }

    /**
     * As the user I want to lock my budget
     */
    @Test
    fun shouldLockBudget() {
        // given
        val budget = Budget.create(2025, Month.FEBRUARY, Money(BigDecimal("100.00"), "PLN"))

        // when
        val lockedBudget = budget.lock()

        // then
        assertTrue(lockedBudget.isSuccess)
        assertFalse(budget.locked)
        assertEquals(budget.uuid, lockedBudget.getOrThrow().uuid)
        assertTrue(lockedBudget.getOrThrow().locked)
    }

    @Test
    fun shouldNotChangeLimitOfLockedBudget() {
        // given
        val budget = Budget.create(2025, Month.FEBRUARY, Money(BigDecimal("100.00"), "PLN"))
        val lockedBudget = budget.lock().getOrThrow()

        // when
        val result = lockedBudget.changeLimit(Money(BigDecimal("200.00"), "PLN"))

        // then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalStateException)
    }

    @Test
    fun shouldNotLockAlreadyLockedBudget() {
        // given
        val budget = Budget.create(2025, Month.FEBRUARY, Money(BigDecimal("100.00"), "PLN"))
        val lockedBudget = budget.lock().getOrThrow()

        // when
        val result = lockedBudget.lock()

        // then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalStateException)
    }

    @Test
    fun shouldAddSpendingToBudget() {
        // given
        val budget = Budget.create(2025, Month.FEBRUARY, Money(BigDecimal("1000.00"), "PLN"))
        val spending01 = Spending("Groceries", Money(BigDecimal("200.00"), "PLN"))
        val spending02 = Spending("Groceries", Money(BigDecimal("200.00"), "PLN"))

        // when
        val budget01 = budget.addSpending(spending01).getOrThrow()
        val budget02 = budget01.addSpending(spending02).getOrThrow()

        // then
        assertTrue(spending01 in budget02.spendings)
        assertTrue(spending02 in budget02.spendings)
        assertEquals(Money(BigDecimal("400.00"), "PLN"), budget02.totalSpendAmount)
    }
}