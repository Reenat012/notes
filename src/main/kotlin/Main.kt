fun main(args: Array<String>) {

}

class Notes<A, B> (
    private var title: A,
    private var text: B,
    private val noteId: Int
) {
    //создаем пустой массив для хранения notes
    private var notes = emptyArray<Notes<A, B>>()

    //вводим функцию добавления notes
    fun add(note: Notes<A, B>) : Notes<A, B> {
        notes += note
        return notes.last()
    }

    fun createComment()


}