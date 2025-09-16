package wfarganu.personalfinancetracker.budget.infrastructure

import jakarta.persistence.*
import wfarganu.personalfinancetracker.shared.domain.Money
import wfarganu.personalfinancetracker.shared.infrastructure.AuditableEntity
import java.time.Month
import java.util.*

@Entity
@Table(name = "budgets")
internal class BudgetEntity(
    @Id
    val uuid: UUID = UUID.randomUUID(),

    var year: Int = 0,

    @Enumerated(EnumType.STRING)
    var month: Month? = null,

    @Embedded
    var limit: Money = Money.ZERO,

    var locked: Boolean = false,

    @OneToMany(mappedBy = "budget", cascade = [CascadeType.ALL], orphanRemoval = true)
    var spendings: MutableList<SpendingEntity> = mutableListOf()
) : AuditableEntity() {
    constructor(uuid: UUID) : this(
        uuid = uuid,
        year = 0,
        month = null,
        limit = Money.ZERO,
        locked = false,
        spendings = mutableListOf()
    )
}
