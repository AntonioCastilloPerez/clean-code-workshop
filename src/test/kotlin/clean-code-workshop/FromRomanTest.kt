package clean

import `clean-code-workshop`.FromRoman
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FromRomanTest {

    @Test
    fun `check correct transformation of roman number with just one character`() {
        Assertions.assertThat(FromRoman().fromRoman("I")).isEqualTo(1)
    }

    @Test
    fun `check correct tranformation of roman number with just two characters`() {
        Assertions.assertThat(FromRoman().fromRoman("IV")).isEqualTo(4)
    }

    @Test
    fun `test behaviour with unknown letter`() {
        Assertions.assertThat(FromRoman().fromRoman("VIO")).isEqualTo("Uknown roman number contained in the character")
    }

    @Test
    fun `check correct transformation of roman number`() {
        Assertions.assertThat(FromRoman().fromRoman("MCMXLV")).isEqualTo(1945)
    }
}
