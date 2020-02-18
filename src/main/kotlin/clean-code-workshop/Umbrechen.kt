package `clean-code-workshop`

class Umbrechen {

    fun umbrechen(text: String, maxLineLength: Int, indent: Boolean): List<String> {

        val formatted = formatLines(textSplitAndCount(text, maxLineLength), maxLineLength)

        return if (indent) justifyText(formatted, maxLineLength) else formatted
    }

    fun textSplitAndCount(text: String, maxLineLength: Int): List<Pair<String, Int>> {
        return text.split(" ").map { it.chunked(maxLineLength) }.flatten().map { it to it.length }
    }

    fun formatLines(input: List<Pair<String, Int>>, maxLineLength: Int): List<String> {
        var countLines = 0
        val formattedList = mutableListOf("")

        input.map { it }.fold(0, { total, next ->
            if (total.plus(next.second + 1) > maxLineLength) {
                countLines += 1
                formattedList.add(countLines, next.first)
                next.second
            } else {
                formattedList[countLines] = formattedList[countLines] + " " + next.first
                total.plus(next.second + 1)
            }
        })

        return formattedList.map { it.trimIndent() }
    }

    private fun justifyText(formatted: List<String>, maxLineLength: Int): List<String> {
        val numOfCharsInText = formatted.fold(0) { sum, element -> sum.plus(element.length) }
        val numberOfExpectedCharsInText = formatted.size * maxLineLength

        return if (numOfCharsInText != numberOfExpectedCharsInText) {
            justifyText(formatted.map { justifyLine(it, maxLineLength) }, maxLineLength)
        } else return formatted
    }

    private fun justifyLine(it: String, maxLineLength: Int): String {
        return when {
            it.length < maxLineLength -> addBlanks(it, maxLineLength)
            it.length > maxLineLength -> subtractBlanks(it, maxLineLength)
            else -> it
        }
    }

    private fun addBlanks(input: String, maxLineLength: Int): String {
        return if (maxLineLength > input.length) {
            addBlanks(input.replace(" ", "  "), maxLineLength)
        } else return input
    }

    private fun subtractBlanks(input: String, maxLineLength: Int): String {
        return if (maxLineLength < input.length) {
            subtractBlanks(input.replaceFirst("  ", " "), maxLineLength)
        } else return input
    }
}
