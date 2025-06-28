package wfarganu.personalfinancetracker.budget.infrastructure

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import wfarganu.personalfinancetracker.budget.domain.core.Budget
import wfarganu.personalfinancetracker.budget.domain.ports.IBudgetRepository

/**
 * I prefer to use EntityManager directly for more control over the persistence context
 */
@Repository
internal class BudgetRepositoryRepository(
    private val entityManager: EntityManager) : IBudgetRepository {

    // Consider using BudgetMapper as bean to show all dependencies in constructor
    override fun save(budget: Budget): Budget {
        BudgetMapper.toEntity(budget).let {
            entityManager.persist(it)
            return BudgetMapper.toDomain(it)
        }
    }

    override fun findByUuid(uuid: String): Budget? {
        TODO("Not yet implemented")
    }

    override fun deleteByUuid(uuid: String) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Budget> {
        TODO("Not yet implemented")
    }
}