package clean

import `clean-code-workshop`.FromRoman
import `clean-code-workshop`.TabulateCSV
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TabulateCSVTest {

    @Test
    fun `check correct transformation of roman number with just one character`() {
        val inputCSVList = listOf<String>(
                "",
                "",
                ""
        )

        val expectedCSVList = listOf<String>(
                "",
                "",
                ""
        )

        Assertions.assertThat(TabulateCSV().tabelliere(inputCSVList)).isEqualTo(expectedCSVList)
    }

    @Test
    fun `check that a function gives back the the size of the columns inside a list of strings`() {

        val inputCSVListed = listOf<List<String>>(
                listOf("Name","Strasse","Ort","Alter"),
                listOf("Peter Pan","Am Hang 5","12345 Einsam","42"),
                listOf("Maria Schmitz","Kölner Straße 45","50123 Köln","43"),
                listOf("Paul Meier","Münchener Weg 1","87654 München","65")
        )

        val expectedListOfSizes = listOf(
                13,
                16,
                13,
                5
        )

        Assertions.assertThat(TabulateCSV().getColumnSizes(inputCSVListed)).isEqualTo(expectedListOfSizes)
    }

}
