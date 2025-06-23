package wfarganu.personalfinancetracker.budget.infrastructure

import wfarganu.personalfinancetracker.budget.domain.core.Budget

interface IBudgetRepository {

    /**
     * Saves a budget entity to the repository.
     *
     * @param budget The budget entity to save.
     * @return The saved budget entity.
     */
    fun save(budget: Budget): Budget

    /**
     * Finds a budget entity by its UUID.
     *
     * @param uuid The UUID of the budget entity to find.
     * @return The found budget entity, or null if not found.
     */
    fun findByUuid(uuid: String): Budget?

    /**
     * Deletes a budget entity by its UUID.
     *
     * @param uuid The UUID of the budget entity to delete.
     */
    fun deleteByUuid(uuid: String)

    /**
     * Finds all budget entities.
     *
     * @return A list of all budget entities.
     */
    fun findAll(): List<Budget>
}