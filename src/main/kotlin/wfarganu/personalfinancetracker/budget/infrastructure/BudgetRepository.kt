package wfarganu.personalfinancetracker.budget.infrastructure

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import wfarganu.personalfinancetracker.budget.domain.core.Budget

/**
 * I prefer to use EntityManager directly for more control over the persistence context
 */
@Repository
internal class BudgetRepository(
    private val entityManager: EntityManager,
    private val budgetMapper: BudgetMapper) : IBudgetRepository {

    override fun save(budget: Budget): Budget {
        budgetMapper.toEntity(budget).let {
            entityManager.persist(it)
            return budgetMapper.toDomain(it)
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