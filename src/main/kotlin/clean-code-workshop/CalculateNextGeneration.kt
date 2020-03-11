package `clean-code-workshop`

import java.io.File

typealias booleanMatrix = Array<Array<Boolean>>

data class CellStatus(
        val alive: Boolean = true,
        val dead: Boolean = false
)

data class Operators(
        val equalTo: Int = 0,
        val lowerThan: Int = -1,
        val greaterThan: Int = 1
)

class CalculateNextGeneration {

    fun calculateNextGeneration(path: String) {

        return path.fileToBooleanMatrix()
                .killOverPopulatedCells()
                .killUnderpopulatedCells()
                .permitCellLive(Pair(2, 3))
                .resurrectCells()
                .booleanMatrixToFile()
    }

    fun String.fileToBooleanMatrix(): booleanMatrix {
        val txt = File(this).useLines { it.toList() }
        return txt.map { it -> it.chunked(1).map { it == "*" }.toTypedArray() }.toTypedArray()
    }

    private fun booleanMatrix.booleanMatrixToFile() {
        val toPrint = this.joinToString(separator = "\n") { it -> it.joinToString("") { if (it) "*" else "." } }
        File("src/test/resources/output.txt").writeText(toPrint)
    }

    fun booleanMatrix.killOverPopulatedCells(): booleanMatrix {
        return operateOnMatrix(this, Operators().greaterThan, 3, Pair(CellStatus().dead, "kill"))
    }

    fun booleanMatrix.killUnderpopulatedCells(): booleanMatrix {
        return operateOnMatrix(this, Operators().lowerThan, 2, Pair(CellStatus().dead, "kill"))
    }

    fun booleanMatrix.permitCellLive(limitsToPermit: Pair<Int, Int>): booleanMatrix {
        val output = operateOnMatrix(this, Operators().equalTo, limitsToPermit.first, Pair(CellStatus().alive, "permit"))
        return operateOnMatrix(output, Operators().equalTo, limitsToPermit.second, Pair(CellStatus().alive, "permit"))
    }

    fun booleanMatrix.resurrectCells(): booleanMatrix {
        return operateOnMatrix(this, Operators().equalTo, 3, Pair(CellStatus().alive, "resurrect"))
    }

    private fun operateOnMatrix(matrix: booleanMatrix, operator: Int, limit: Int, killOrResurrect: Pair<Boolean, String>): booleanMatrix {
        val rows = matrix.size
        val columns = matrix[0].size

        for (row in 0 until rows) {
            for (col in 0 until columns) {
                val numberOfLivingNeighbours = countLivingNeighboursForCell(matrix, row, col)
                decideMatrixCellStatus(numberOfLivingNeighbours, limit, operator, killOrResurrect, matrix, row, col)
            }
        }

        return matrix
    }

    private fun decideMatrixCellStatus(numberOfLivingNeighbours: Int, limit: Int, operator: Int, killOrResurrect: Pair<Boolean, String>, matrix: booleanMatrix, row: Int, col: Int) {
        if (numberOfLivingNeighbours.compareTo(limit) == operator) {
            if (operator == 0 && killOrResurrect.second == "permit") {
                matrix[row][col] = matrix[row][col]
            } else {
                matrix[row][col] = killOrResurrect.first
            }
        } else {
            matrix[row][col] = matrix[row][col]
        }
    }

    fun countLivingNeighboursForCell(matrix: booleanMatrix, row: Int, col: Int): Int {
        var count = 0

        for (x in -1..1) {
            for (y in -1..1) {
                if (Pair(row + x, col + y) != Pair(row, col)) {
                    try {
                        if (matrix[row + x][col + y]) count++
                    } catch (e: ArrayIndexOutOfBoundsException) {
                    }
                }
            }
        }

        return count
    }

}
