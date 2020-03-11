package `clean-code-workshop`

import org.assertj.core.api.Assertions
import org.junit.Test
import java.io.File


class CalculateNextGenerationTest {


    @Test
    fun `Calculates a next generation`() {

        val path = ("src/test/resources/input.txt")

        CalculateNextGeneration().calculateNextGeneration(path)

        Assertions.assertThat(File("src/test/resources/expectedOutput.txt").readText().trim())
                .isEqualTo(File("src/test/resources/output.txt").readText().trim())

    }

    @Test
    fun `Parses a next generation txt file into a boolean array of array`() {

        val path = "src/test/resources/input.txt"

        val expectedBooleanMatrix: booleanMatrix = arrayOf(
                arrayOf(false, false, false, false, false, false, false, false),
                arrayOf(false, false, false, false, true, false, false, false),
                arrayOf(false, false, false, true, true, false, false, false),
                arrayOf(false, false, false, false, false, false, false, false)
        )

        CalculateNextGeneration().apply {
            Assertions.assertThat(path.fileToBooleanMatrix()).isEqualTo(expectedBooleanMatrix)

        }
    }

    @Test
    fun `Any live cell with fewer than two live neighbours dies caused by underpopulation`() {


        val testMatrix = arrayOf<Array<Boolean>>(
                arrayOf(true, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, true, false, false),
                arrayOf(true, false, false, true)
        )

        val expectedMatrix = arrayOf<Array<Boolean>>(
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, true, false, false),
                arrayOf(true, false, false, false)
        )

        CalculateNextGeneration().apply {
            Assertions.assertThat(testMatrix.killUnderpopulatedCells()).isEqualTo(expectedMatrix)
        }
    }

    @Test
    fun `Any live cell with fewer than two live neighbours dies caused by overpopulation`() {

        val testMatrix = arrayOf<Array<Boolean>>(
                arrayOf(false, false, false, false),
                arrayOf(true, true, false, false),
                arrayOf(true, true, false, false),
                arrayOf(true, true, false, true)
        )

        val expectedMatrix = arrayOf<Array<Boolean>>(
                arrayOf(false, false, false, false),
                arrayOf(true, true, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, true, false, true)

        )

        CalculateNextGeneration().apply {
            Assertions.assertThat(testMatrix.killOverPopulatedCells()).isEqualTo(expectedMatrix)
        }

    }

    @Test
    fun `resurrect a dead cell with exactly three living neighbouts`() {


        val testBooleanMatrix = arrayOf<Array<Boolean>>(
                arrayOf(false, false, false),
                arrayOf(true, true, false),
                arrayOf(true, false, false)
        )

        val expectedMatrix = arrayOf<Array<Boolean>>(
                arrayOf(false, false, false),
                arrayOf(true, true, false),
                arrayOf(true, true, false)
        )

        CalculateNextGeneration().apply {
            Assertions.assertThat(testBooleanMatrix.resurrectCells()).isEqualTo(expectedMatrix)
        }

    }

    @Test
    fun `a live cell with two or three living neighbours lives to the next generation`() {


        val testBooleanMatrix = arrayOf<Array<Boolean>>(
                arrayOf(true, true, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false)
        )

        val expectedMatrix = arrayOf<Array<Boolean>>(
                arrayOf(true, true, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false)
        )

        CalculateNextGeneration().apply {
            Assertions.assertThat(testBooleanMatrix.permitCellLive(Pair(2,3))).isEqualTo(expectedMatrix)
        }

    }

    @Test
    fun `Count living neighbours from specific row and columns`() {


        val testBooleanMatrix = arrayOf<Array<Boolean>>(
                arrayOf(true, false, false),
                arrayOf(true, false, false),
                arrayOf(false, false, false)
        )

        val actualLivingNeighboursTest1 = CalculateNextGeneration().countLivingNeighboursForCell(testBooleanMatrix, 1, 1)
        val actualLivingNeighboursTest2 = CalculateNextGeneration().countLivingNeighboursForCell(testBooleanMatrix, 0, 0)

        Assertions.assertThat(actualLivingNeighboursTest1).isEqualTo(2)
        Assertions.assertThat(actualLivingNeighboursTest2).isEqualTo(1)

    }
}
