package ru.data.Attachment

class Photo(
    override val type: String = "Photo",
    private val id: Int,
    private val albumId: Int,
    private val ownerId: Int,
    private val userId: Int,
    private val text: String
) : Attachment {


    fun photoDescription(): String {
        return this.text
    }

    override fun getAttachmentType(): String {
        return "Attachment type is - $type"
    }
}