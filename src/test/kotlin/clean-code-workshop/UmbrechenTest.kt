package `clean-code-workshop`

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UmbrechenTest {

    @Test
    fun `from a string create a list with every element`() {

        val input = "Es blaut die Nacht,"

        val expectedOutput = listOf(
                Pair("Es", 2),
                Pair("blaut", 5),
                Pair("die", 3),
                Pair("Nacht,", 6)
        )

        Assertions.assertThat(Umbrechen().textSplitAndCount(input, maxLineLength = 9)).isEqualTo(expectedOutput)

    }

    @Test
    fun `from a string create a list with every element with words larger than the max length`() {

        val input = "Es blaut die Nacht,"

        val expectedOutput = listOf(
                Pair("Es", 2),
                Pair("bla", 3),
                Pair("ut", 2),
                Pair("die", 3),
                Pair("Nac", 3),
                Pair("ht,", 3)
        )

        val maxLength = 3

        Assertions.assertThat(Umbrechen().textSplitAndCount(input, maxLength)).isEqualTo(expectedOutput)

    }

    @Test
    fun `create List with output lines`() {

        val input = listOf(
                Pair("Es", 2),
                Pair("blaut", 5),
                Pair("die", 3),
                Pair("Nacht,", 6),
                Pair("die", 3),
                Pair("Sternlein", 9),
                Pair("blinken,", 8)
        )
        val maxlength = 9

        val expectedOutput = listOf(
                "Es blaut",
                "die",
                "Nacht,",
                "die",
                "Sternlein",
                "blinken,"
        )
        Assertions.assertThat(Umbrechen().formatLines(input, maxlength)).isEqualTo(expectedOutput)

    }

    @Test
    fun `format a String without indentation with maximum length`() {

        val input = "Es blaut die Nacht, " +
                "die Sternlein blinken, " +
                "Schneeflöcklein leis hernieder sinken."
        val maxlength = 9

        val expectedOutput = listOf(
                "Es blaut",
                "die",
                "Nacht,",
                "die",
                "Sternlein",
                "blinken,",
                "Schneeflö",
                "cklein",
                "leis",
                "hernieder",
                "sinken."
        )
        Assertions.assertThat(Umbrechen().umbrechen(input, maxlength, false)).isEqualTo(expectedOutput)

    }

    @Test
    fun `format a String with justification`() {

        val input = "Es blaut die Nacht, " +
                "die Sternlein blinken, " +
                "Schneeflöcklein leis hernieder sinken."
        val maxlength = 22

        val expectedOutput = listOf(
                "Es  blaut  die  Nacht,",
                "die Sternlein blinken,",
                "Schneeflöcklein   leis",
                "hernieder      sinken."
        )
        Assertions.assertThat(Umbrechen().umbrechen(input, maxlength, true)).isEqualTo(expectedOutput)

    }

}
