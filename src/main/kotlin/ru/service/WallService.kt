package ru.service

import ru.customExceptions.PostNotFoundException
import ru.data.Attachment.*
import ru.data.Comment
import ru.data.Post

class WallService {
    private var posts = emptyArray<Post>()
    private var generatedId = Int.MIN_VALUE //MIN_VALUE for more coverage
    private var attachments = arrayListOf<Attachment>()
    private var comments = emptyArray<Comment>()

    fun createComment(comment: Comment) {
        for (post in posts) {
            if (comment.postId == post.id) {
                comments += comment
                return
            }
        }
        throw PostNotFoundException("Post not found")
    }

    fun printComments() {
        for (comment in comments) {
            println(comment)
        }
    }

    fun addAttachment(attachment: Attachment) {
        attachments.add(attachment)
    }

    fun allAttachmentsActions() {
        //Do common actions for all attachments and unique for each class
        for (attachment in attachments) {
            println(attachment.getAttachmentType())
            when (attachment) {
                is Photo -> println(attachment.photoDescription())
                is Video -> println(attachment.videoDescription())
                is Link -> println(attachment.linkDescription())
                is Poll -> println(attachment.poolDescription())
                is Note -> println(attachment.noteDescription())
                else -> println("Attachment don't contain info or description")
            }
        }
    }

    fun add(post: Post): Post {
        //hardcoded value for skipping zero ID
        if (generatedId == -1) {
            generatedId = 1
            val postWithId: Post = post.copy(id = -1)
            posts += postWithId
            return postWithId
        }
        val postWithId: Post = post.copy(id = generatedId++)
        posts += postWithId
        return postWithId
    }

    fun printPosts() {
        for (post in posts) {
            println(post)
        }
    }

    fun update(post: Post): Boolean {
        for ((index, value) in posts.withIndex()) {
            if (post.id == value.id) {
                val originalDate = value.date
                val originalOwnerId = value.ownerId
                val tempPost: Post = post.copy(date = originalDate, ownerId = originalOwnerId)
                posts[index] = tempPost
                return true
            }
        }
        return false
    }
}
