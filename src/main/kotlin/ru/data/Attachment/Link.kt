package ru.data.Attachment

class Link(
    override val type: String,
    private val url: String,
    private val title: String,
    private val caption: String?,
    private val description: String,
    private val photo: Photo
) : Attachment {

    fun linkDescription(): String {
        return this.description
    }

    override fun getAttachmentType(): String {
        return "Attachment type is - $type"
    }
}