package wfarganu.personalfinancetracker.budget.domain.core

import wfarganu.personalfinancetracker.budget.domain.BUDGET_ALREADY_LOCKED
import wfarganu.personalfinancetracker.budget.domain.CANNOT_UPDATE_LOCKED_BUDGET
import java.time.Month
import java.util.*

// Primary constructor
class Budget private constructor(
    val uuid: UUID,
    val year: Int,
    val month: Month,
    // limit is not a class property, but a constructor parameter
    limit: Money,
    locked: Boolean = false) {

    // Factory method to create a new Budget instance - equivalent to a static method in Java
    companion object {
        fun create(year: Int, month: Month, limit: Money): Budget {
            return Budget(UUID.randomUUID(), year, month, limit)
        }
    }
    // class property with private setter
    var limit = limit
    private set

    @get:JvmName("isLocked") // This annotation allows the property to be accessed as isLocked in Java
    var locked = locked
        private set

    // Function defined using expression body syntax
    // If is the expression in the Kotlin meaning it can be returned, assigned, passed as a parameter, etc.
    fun changeLimit(limit: Money): Result<Budget> =
        if (locked) Result.failure(IllegalStateException(CANNOT_UPDATE_LOCKED_BUDGET))
        else Result.success(Budget(uuid, year, month, limit, locked))

    // Function defined using block body syntax - return keyword is mandatory here
    fun lock(): Result<Budget> {
        return if (locked) Result.failure(IllegalStateException(BUDGET_ALREADY_LOCKED))
        else Result.success(Budget(uuid, year, month, limit, true))
    }
}