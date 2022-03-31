package ru

import org.junit.*
import org.junit.Assert.*
import ru.customExceptions.PostNotFoundException
import ru.data.Attachment.Photo
import ru.data.Comment
import ru.data.Likes
import ru.data.Post
import ru.data.Repost
import ru.service.WallService

class WallServiceTest {
    private val service = WallService()
    private val photoAtt =
        Photo(type = "Photo", id = 1, albumId = 2, ownerId = 3, userId = 4, text = "Photo description")

    //Comment with incorrect postId
    private val comment = Comment(
        postId = 0,
        id = 0,
        fromId = 212,
        date = 442315,
        text = "Comment text",
        replyToUser = 39,
        replyToComment = 53,
        attachments = photoAtt
    )
    private val original = Post(
        id = 0,
        ownerId = 6,
        fromId = 1,
        createdBy = 1,
        date = 2342562,
        text = "lorem ipsum",
        replyOwnerId = 1,
        replyPostId = 1,
        friendsOnly = true,
        //Dummy comment
        comment = Comment(
            postId = -2147483648,
            id = 6,
            fromId = 212,
            date = 442315,
            text = "Comment text",
            replyToUser = 39,
            replyToComment = 53,
            attachments = photoAtt
        ),
        likes = Likes(1, userLikes = true, canLike = true, canPublish = true),
        reposts = Repost(1, userReposted = true),
        postType = "Post",
        signerId = 1,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = true,
        markedAsAds = true,
        isFavorite = true
    )

    @Test(expected = PostNotFoundException::class)
    //createCommentThrow
    fun shouldThrow() {
        service.add(original)
        service.createComment(comment)
    }

    @Test
    //createCommentTest
    fun shouldNotThrow() {
        val addedPost = service.add(original)
        service.createComment(comment.copy(postId = addedPost.id))
        assertNotSame(Unit::class, PostNotFoundException::class)
    }

    @Test
    fun addTest() {
        val addedPost = service.add(original)
        val unexpectedId = 0
        assertNotEquals(unexpectedId, addedPost.id)
    }

    @Test
    fun updateNotExistTest() {
        service.add(original)
        val update = original.copy(id = 0)
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun updateExistTest() {
        val update = service.add(original)
        val result = service.update(update)
        assertTrue(result)
    }
}

