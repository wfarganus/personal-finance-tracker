package wfarganu.personalfinancetracker.ddd

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Service
@Transactional
annotation class ApplicationService()
