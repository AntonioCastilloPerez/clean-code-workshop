package clean

import `clean-code-workshop`.FromRoman
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FromRomanTest {

    @Test
    fun `negate roman list`() {

        val cleanCodeTrainingTest = FromRoman().negateArray(listOf(1, 2, 3, 4))

        Assertions.assertThat(cleanCodeTrainingTest).isEqualTo((listOf(-1, -2, -3, 4)))
    }

    @Test
    fun `map char of roman letter to numbers`() {

        val cleanCodeTrainingTest = FromRoman().mapFromRomanLetterToNumber("IVXLCDM")

        Assertions.assertThat(cleanCodeTrainingTest).isEqualTo((listOf(1, 5, 10, 50, 100, 500, 1000)))
    }

    @Test
    fun `check correct transformation of roman number with just one character`() {

        val cleanCodeTrainingTest = FromRoman().fromRoman("I")

        Assertions.assertThat(cleanCodeTrainingTest).isEqualTo(1)

    }

    @Test
    fun `check correct tranformation of roman number with just two characters`() {

        val cleanCodeTrainingTest = FromRoman().fromRoman("IV")

        Assertions.assertThat(cleanCodeTrainingTest).isEqualTo(4)

    }

    @Test
    fun `test behaviour with unknown letter`() {

        val cleanCodeTrainingTest = FromRoman().fromRoman("VIO")

        Assertions.assertThat(cleanCodeTrainingTest).isEqualTo("Uknown roman number contained in the character")

    }

    @Test
    fun `check correct transformation of roman number`() {

        val cleanCodeTrainingTest = FromRoman().fromRoman("MCMXLV")

        Assertions.assertThat(cleanCodeTrainingTest).isEqualTo(1945)

    }
}
