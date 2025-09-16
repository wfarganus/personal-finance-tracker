package wfarganu.personalfinancetracker.budget.infrastructure

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import wfarganu.personalfinancetracker.shared.domain.Money
import wfarganu.personalfinancetracker.shared.infrastructure.AuditableEntity
import java.util.UUID

@Entity
@Table(name = "spendings")
internal class SpendingEntity(
    @Id
    val uuid: UUID = UUID.randomUUID(),

    var name: String = "",

    @Embedded
    var amount: Money = Money.ZERO,

    @ManyToOne
    @JoinColumn(name = "budget_uuid", nullable = false)
    val budget: BudgetEntity
) : AuditableEntity()