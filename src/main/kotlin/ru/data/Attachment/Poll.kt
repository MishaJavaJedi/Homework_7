package ru.data.Attachment

class Poll(
    override val type: String,
    private val id: Int,
    private val ownerId: Int,
    private val created: Int,
    private val question: String,
    private val votes: Int
) : Attachment {
    fun poolDescription(): String {
        return this.question
    }

    override fun getAttachmentType(): String {
        return "Attachment type is - $type"
    }


}