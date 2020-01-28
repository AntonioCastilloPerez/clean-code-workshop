package `clean-code-workshop`

class FromRoman {

    private val romanMap = mapOf<String, Int>((
            ("I" to 1)),
            ("V" to 5),
            ("X" to 10),
            ("L" to 50),
            ("C" to 100),
            ("D" to 500),
            ("M" to 1000)
    )

    fun fromRoman(romanString: String): Any {
        return try {
            val mappedRomanNumber = mapFromRomanLetterToNumber(romanString)
            val negatedRomanNumber = negateArray(mappedRomanNumber)
            negatedRomanNumber.sum()

        } catch (e: NullPointerException) {
            "Uknown roman number contained in the character"
        }

    }

    fun negateArray(input: List<Int>): List<Int> {
        return input.zipWithNext { a, b ->
            if (a < b) {
                a * -1
            } else a
        }.plus(input.last())
    }

    fun mapFromRomanLetterToNumber(romanChar: String): List<Int> {
        val singleRomanNumbers = romanChar.chunked(1)
        return singleRomanNumbers.map { x -> romanMap[x]!! }
    }

}
