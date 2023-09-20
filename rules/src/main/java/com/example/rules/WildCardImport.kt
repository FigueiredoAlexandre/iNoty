package com.example.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtImportAlias
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

private const val WILD_CARD_IMPORT = "WildCardImport"
private const val WILD_CARD_IMPORT_DESCRIPTION = "This rule repors a wild card import"

class WildCardImport(config: Config): Rule(config) {

    override val issue: Issue = Issue(
        id = WILD_CARD_IMPORT,
        severity = Severity.CodeSmell,
        description = WILD_CARD_IMPORT_DESCRIPTION,
        Debt.TWENTY_MINS
    )

    override fun visitImportList(importList: KtImportList) {
        super.visitImportList(importList)
        val wildCardImport = importList.imports.filter { it.isWildCard() }
        wildCardImport.forEach { import ->
            report(
                CodeSmell(
                    issue,
                    Entity.from(import),
                    "Importacao curinga! Explicite quais membros esta importando!!"
                )
            )
        }
    }

    private fun KtImportDirective.isWildCard(): Boolean {
        return importPath.toString().endsWith("*")
    }
}