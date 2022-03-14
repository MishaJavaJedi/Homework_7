package ru.data.Attachment

class Note(
    override val type: String,
    private val id: Int,
    private val ownerId: Int,
    private val title: String,
    private val text: String,
    private val date: Int,
    private val comments: Int
) : Attachment {

    fun noteDescription(): String {
        return this.text
    }

    override fun getAttachmentType(): String {
        return "Attachment type is - $type"
    }
}