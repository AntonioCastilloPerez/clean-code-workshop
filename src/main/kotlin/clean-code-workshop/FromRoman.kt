package `clean-code-workshop`

class FromRoman {

    fun fromRoman(romanString: String): Any {
        return try {
            val mappedRomanNumber = mapFromRomanLetterToNumber(romanString)
            val negatedRomanNumber = negate(mappedRomanNumber)
            negatedRomanNumber.sum()

        } catch (e: NullPointerException) {
            "Uknown roman number contained in the character"
        }
    }

    private fun negate(input: List<Int>): List<Int> {
        return input.zipWithNext { a, b -> if (a < b) { a * -1 } else a }.plus(input.last())
    }

    private fun mapFromRomanLetterToNumber(romanChar: String): List<Int> {
        return romanChar.chunked(1).map { x -> romanMap[x]!! }
    }

    private val romanMap = mapOf((
            ("I" to 1)),
            ("V" to 5),
            ("X" to 10),
            ("L" to 50),
            ("C" to 100),
            ("D" to 500),
            ("M" to 1000)
    )


}
