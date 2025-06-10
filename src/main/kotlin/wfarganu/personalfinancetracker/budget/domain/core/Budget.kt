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
    locked: Boolean = false,
    private val _spendings: MutableList<Spending> = mutableListOf()
) {

    // Factory method to create a new Budget instance - equivalent to a static method in Java
    companion object {
        fun create(year: Int, month: Month, limit: Money): Budget {
            // Named arguments allow to omit the parameters with default values
            return Budget(UUID.randomUUID(), year, month, limit)
        }
    }

    // This is a backing property for the spendings list but returns always fixed size list
    // It is evaluated only once at initialization
    // val spendings = _spendings.toList()

    // To have always updated view of the spendings list, we can use a custom getter
    // It has no backing field, so it is not stored in memory, fresh copy every time
    // To adhere to immutability principles, we return an immutable copy of the list
    val spendings: List<Spending>
        get() = _spendings.toList() // Returns an immutable copy of the spendings list

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
        else Result.success(Budget(uuid, year, month, limit, locked, _spendings))

    // Function defined using block body syntax - return keyword is mandatory here
    fun lock(): Result<Budget> {
        return if (locked) Result.failure(IllegalStateException(BUDGET_ALREADY_LOCKED))
        else Result.success(Budget(uuid, year, month, limit, true, _spendings))
    }
}