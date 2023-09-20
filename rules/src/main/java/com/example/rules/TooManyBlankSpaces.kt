package com.example.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtFile

private const val MAX_BLANK_SPACES = 1
private const val UNNECESSARY_SPACE_MESSAGE = "Unecessary space"
private const val TOO_MANY_BLANK_SPACES = "TooManyBlankSpaces"

class TooManyBlankSpaces(config: Config) : Rule(config) {

    private var blankSpacesCount = 0

    override val issue: Issue = Issue(
        TOO_MANY_BLANK_SPACES,
        Severity.Style,
        "This rule imports too many blank spaces between lines of code",
        Debt.TWENTY_MINS
    )

    override fun visitKtFile(file: KtFile) {
        super.visitKtFile(file)

        val lines = file.text.split("\n")
        for (line in lines) {
            if (line.isBlank()) {
                blankSpacesCount++
            } else {
                if (blankSpacesCount > MAX_BLANK_SPACES) {
                    report(
                        CodeSmell(
                            issue,
                            Entity.from(file),
                            UNNECESSARY_SPACE_MESSAGE
                        )
                    )
                    blankSpacesCount = 0
                }
            }
        }
    }
}