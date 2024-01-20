import java.lang.RuntimeException

fun main(args: Array<String>) {
}

data class Notes<A, B>(
    var title: A,
    var text: B,
    var noteId: Int = 0,
    var comment: Comments?
)

data class Comments(var commentId: Int = 0)

class NotesService {
    //создаем пустой список для хранения notes
    private val notes: MutableList<Notes<String, String>> = mutableListOf()

    //создаем пустой список для хранения комментариев
    private val comments: MutableList<Comments> = mutableListOf()

    //создаем пустой список для хранения удаленных comments
    private var commentsDelete: List<Comments> = listOf()

    //создаем счетчик для noteId на уровне со списком notes чтобы счетчик каждый раз не создавался заново
    var counterNoteId = 0

    //создаем счетчик для commentId на уровне со списком notes чтобы счетчик каждый раз не создавался заново
    var counterCommentId = 0


    //вводим функцию добавления note
    fun add(note: Notes<String, String>): Int {
        notes += note //добавляем note в список notes
        counterNoteId += 1 //присваиваем
        note.noteId = counterNoteId
        return note.noteId
    }

    //вводим функцию добавления комментария к note
    fun createComment(noteId: Int, comment: Comments): Int {
        for (item in notes) {
            if (item.noteId == noteId) {
                comments += comment
                counterCommentId += 1
                comment.commentId = counterCommentId
                return comment.commentId
            }
        }
        throw NoteNotFoundException("Такого noteId не существует")
    }

    //вводим функцию удаление notes
    fun delete(noteId: Int, comment: Comments?): Int {
        for ((index, item) in notes.withIndex()) {
            if (item.noteId == noteId) {
                notes.removeAt(index) //удаляем note из notes
                //т.к. comments
                if (comment !== null) {
                    item.comment?.let { deleteComment(it.commentId) }
                } //удаляем комментарий note
                return 1
            }
        }
        throw NoteNotFoundException("Такого noteId не существует")
    }

    //вводим функцию удаление notes
    fun deleteComment(commentId: Int): Int {
        for ((index, item) in comments.withIndex()) {
            if (item.commentId == commentId) {
                commentsDelete += item.copy() //копируем note и добавляем в список с удаленными notes
                comments.removeAt(index) //удаляем note из notes
                return 1
            }

        }
        throw CommentNotFoundException("Такого commentId не существует")
    }

    //вводим функцию редактирования note
    fun edit(noteId: Int, note: Notes<String, String>) : Int{
        for ((index, item) in notes.withIndex()) {
            if (item.noteId == noteId) {
                notes[index] = note.copy() //присваиваем note новое значение
                return 1
            }
        }
        throw NoteNotFoundException("Такого noteId не существует")
    }

    //вводим функцию редактирования комментария
    fun editComment(commentId: Int, comment: Comments) : Int{
        for ((index, item) in comments.withIndex()) {
            if (item.commentId == commentId) {
                comments[index] = comment.copy() //присваиваем note новое значение
                return 1
            }
        }
        throw CommentNotFoundException("Такого commentId не существует")
    }

    //вводим функцию, возвращающую список заметок, созданный пользователем
    fun get() : MutableList<Notes<String, String>> {
        return notes
    }

    //вводим функцию, которая будет возвращать заметку по ее noteId
    fun getById(noteId: Int) : Notes<String, String> {
        for ((index, item) in notes.withIndex()) {
            if (item.noteId == noteId) {
                return item
            }
        }
        throw NoteNotFoundException("Такого noteId не существует")
    }

    //вводим функцию, которая будет возвращать комментарий к заметке по ее noteId
    fun getСomments(noteId: Int) : String {
        for ((index, item) in notes.withIndex()) {
            if (item.noteId == noteId) {
                return comments.joinToString()
            }
        }
        throw NoteNotFoundException("Такого noteId не существует")
    }

    //вводим функцию, которая будет возвращать удаленные комментарии
    fun restoreComment(commentId: Int) : Int {
        for ((index, item) in commentsDelete.withIndex()) {
            if (item.commentId == commentId) {
                comments += item.copy() //возвращаем копию удаленного комментария
                return 1
            }
        }
        throw CommentNotFoundException("Такого commentId не существует")
    }
}

class NoteNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)