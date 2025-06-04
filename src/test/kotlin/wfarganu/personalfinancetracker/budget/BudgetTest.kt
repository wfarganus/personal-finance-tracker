package wfarganu.personalfinancetracker.budget

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.time.Month

class BudgetTest {

    /**
     * As the user I want to change the limit of my budget
     */
    @Test
    fun shouldChangeLimit() {
        // given
        val newLimit = Money(BigDecimal("2000.00"), "PLN")
        val budget = Budget.create(2025, Month.FEBRUARY, Money(BigDecimal("100.00"), "PLN"))

        // when
        val changedBudget = budget.changeLimit(newLimit)

        // then
        assertEquals(budget.uuid, changedBudget.uuid)
        assertTrue(newLimit == changedBudget.limit)
    }
}