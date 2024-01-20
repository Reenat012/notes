import junit.framework.TestCase.*
import org.junit.Test

class NotesServiceTest {

    @Test
    fun add() {
        val service = NotesService()

        val result = service.add(Notes("Title", "Text", comment = Comments()))

        assertEquals(1, result)
    }

    @Test
    fun createComment() {
        val service = NotesService()
        val note = service.add(Notes("Title", "Text", comment = Comments()))

        val result = service.createComment(1, Comments())

        assertEquals(1, result)
    }

    @Test
    fun delete() {
        val service = NotesService()
        val note = service.add(Notes("Title", "Text", comment = Comments()))

        val result = service.delete(1, comment = null)

        assertEquals(1, result)
    }

    @Test
    fun deleteComment() {
        val service = NotesService()
        val note = service.add(Notes("Title", "Text", comment = Comments()))
        val comment = service.createComment(1, Comments())

        val result = service.deleteComment(1)

        assertEquals(1, result)
    }

    @Test
    fun edit() {
        val service = NotesService()
        val note = service.add(Notes("Title", "Text", comment = Comments()))
        val result = service.edit(1, Notes("Название", "Текст", comment = null))

        assertEquals(1, result)
    }

    @Test
    fun editComment() {
        val service = NotesService()
        val note = service.add(Notes("Title", "Text", comment = null))
        val comment = service.createComment(1, Comments())
        val result = service.editComment(1, Comments())

        assertEquals(1, result)
    }

    @Test
    fun get() {
        val service = NotesService()
        val notes = mutableListOf(
            Notes("Title", "Text", 1, comment = null),
            Notes("Название", "Текст", 2, comment = null)
        )

        service.add(Notes("Title", "Text", comment = null))
        service.add(Notes("Название", "Текст", comment = null))

        val result = service.get()

        assertEquals(notes, result)
    }

    @Test
    fun getById() {
        val service = NotesService()
        val notes = mutableListOf(
            Notes("Title", "Text", 1, comment = null),
            Notes("Название", "Текст", 2, comment = null)
        )
        val note = service.add(Notes("Title", "Text", comment = null))
        val result = service.getById(1)

        assertEquals(notes[0], result)
    }

    @Test
    fun getСomments() {
        val service = NotesService()
        val comments = mutableListOf(
            Comments(1)
        )
        val note = service.add(Notes("Title", "Text", comment = null))
        val comment = service.createComment(1, Comments())
        val result = service.getСomments(1)

        assertEquals(comments[0].toString(), result)
    }

    @Test
    fun restoreComment() {
        val service = NotesService()
        val note = service.add(Notes("Title", "Text", comment = Comments()))
        val comment = service.createComment(1, Comments())
        val delete = service.deleteComment(1)

        val result = service.restoreComment(1)

        assertEquals(1, result)
    }
}