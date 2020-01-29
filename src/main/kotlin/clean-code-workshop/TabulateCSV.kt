package `clean-code-workshop`

class TabulateCSV {

    fun tabelliere(inputList: List<String>): List<String> {

        return emptyList()
    }

    fun getColumnSizes(listedColumns: List<List<String>>): List<Int> {

        var numberOfColumns = listedColumns.first().size
        var maxSize = 0
        var sizeList = listOf<Int>()

        for (i in 0 until numberOfColumns) {
            listedColumns.map { list ->
                if (list[i].length > maxSize) {
                    maxSize = list[i].length
                }
            }
            sizeList = sizeList.plus(maxSize)
            maxSize = 0
        }

        return sizeList
    }
}