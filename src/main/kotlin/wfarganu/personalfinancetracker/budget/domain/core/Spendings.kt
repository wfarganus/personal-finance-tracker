package wfarganu.personalfinancetracker.budget.domain.core

data class Spendings(private val _spendings: List<Spending>) {

    val spendings: List<Spending>
        get() = _spendings.toList() // Returns an immutable copy of the spendings list

    val totalSpendAmount: Money
        get() = _spendings.fold(Money.ZERO) { acc, spending -> acc + spending.amount } // Calculate total spend amount

    fun add(spending: Spending): Spendings {
        return Spendings(_spendings + spending) // Returns a new instance with the added spending
    }
}
