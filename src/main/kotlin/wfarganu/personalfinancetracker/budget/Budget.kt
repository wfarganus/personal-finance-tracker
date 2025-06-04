package wfarganu.personalfinancetracker.budget

import java.time.Month
import java.util.*

// Primary constructor
class Budget private constructor(
    val uuid: UUID,
    val year: Int,
    val month: Month,
    limit: Money) {

    companion object {
        fun create(year: Int, month: Month, limit: Money): Budget {
            return Budget(UUID.randomUUID(), year, month, limit)
        }
    }

    // class property with private setter
    var limit = limit
    private set

    // function scope - one-liner for
    fun changeLimit(limit: Money): Budget =
        Budget(uuid, year, month, limit)
}