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
    val limit: Money,
    val locked: Boolean = false,
    private val _spendings: Spendings = Spendings(emptyList())
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
        get() = _spendings.spendings // Returns an immutable copy of the spendings list

    val totalSpendAmount: Money
        get() = _spendings.totalSpendAmount // Returns the total spend amount, which is a class property

    // Function defined using expression body syntax
    // 'if' statement is the expression in the Kotlin meaning it can be returned, assigned, passed as a parameter, etc.
    fun changeLimit(limit: Money): Result<Budget> =
        if (locked) Result.failure(IllegalStateException(CANNOT_UPDATE_LOCKED_BUDGET))
        else Result.success(Budget(uuid, year, month, limit, _spendings = _spendings))

    // Function defined using block body syntax - return keyword is mandatory here
    fun lock(): Result<Budget> {
        return if (locked) Result.failure(IllegalStateException(BUDGET_ALREADY_LOCKED))
        else Result.success(Budget(uuid, year, month, limit, _spendings = _spendings))
    }

    fun addSpending(spending: Spending): Result<Budget> {
        if (locked) {
            return Result.failure(IllegalStateException(CANNOT_UPDATE_LOCKED_BUDGET))
        }
        _spendings.add(spending)
        return Result.success(Budget(uuid, year, month, limit, _spendings = _spendings))
    }
}