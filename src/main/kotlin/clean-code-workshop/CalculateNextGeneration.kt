package `clean-code-workshop`

import java.io.File

typealias booleanMatrix = Array<Array<Boolean>>

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

    fun booleanMatrix.booleanMatrixToFile() {
        val toPrint = this.map { it -> it.map { if (it) "*" else "." }.joinToString("") }.joinToString(separator = "\n")
        File("src/test/resources/output.txt").writeText(toPrint)
    }

    fun booleanMatrix.killOverPopulatedCells(): booleanMatrix {
        return operateOnMatrix(this, 1, 3, Pair(false, "kill"))
    }

    fun booleanMatrix.killUnderpopulatedCells(): booleanMatrix {
        return operateOnMatrix(this, -1, 2, Pair(false, "kill"))
    }

    fun booleanMatrix.permitCellLive(numbersToPermit: Pair<Int, Int>): booleanMatrix {
        val output = operateOnMatrix(this, 0, numbersToPermit.first, Pair(true, "permit"))
        return operateOnMatrix(output, 0, numbersToPermit.second, Pair(true, "permit"))
    }

    fun booleanMatrix.resurrectCells(): booleanMatrix {
        return operateOnMatrix(this, 0, 3, Pair(true, "resurrect"))
    }

    private fun operateOnMatrix(matrix: booleanMatrix, operator: Int, limit: Int, killOrResurrect: Pair<Boolean, String>): booleanMatrix {
        val rows = matrix.size
        val columns = matrix[0].size

        for (row in 0 until rows) {
            for (col in 0 until columns) {
                val numberOfLivingNeighbours = countLivingNeighboursForCell(matrix, row, col)
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
        }

        return matrix
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
