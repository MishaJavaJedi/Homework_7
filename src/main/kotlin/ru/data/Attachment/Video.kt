package ru.data.Attachment

class Video(
    override val type: String = "Video",
    private val id: Int,
    private val ownerId: Int,
    private val title: String,
    private val description: String,
    private val duration: Int
) : Attachment {
    fun videoDescription(): String {
        return this.description
    }

    override fun getAttachmentType(): String {
        return "Attachment type is - $type"
    }
}