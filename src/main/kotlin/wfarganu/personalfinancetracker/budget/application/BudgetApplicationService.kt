package wfarganu.personalfinancetracker.budget.application

import wfarganu.personalfinancetracker.budget.domain.core.Budget
import wfarganu.personalfinancetracker.budget.infrastructure.IBudgetRepository
import wfarganu.personalfinancetracker.ddd.ApplicationService

/**
 * This service handles the application logic for managing budgets. It behaves as operation script,
 * which means it orchestrates the use of domain objects and repositories to perform operations.
 */
@ApplicationService
class BudgetApplicationService(
    private val budgetRepository: IBudgetRepository) {

    /**
     * Saves a budget to the repository.
     *
     * @param budget The budget to save.
     * @return The saved budget.
     */
    fun createEmptyBudget(budgetRequest: CreateBudgetRequest): Budget {
        Budget.create(budgetRequest.year, budgetRequest.month, budgetRequest.limit).let {
            return budgetRepository.save(it)
        }
    }
}