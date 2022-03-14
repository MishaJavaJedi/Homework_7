package ru

import org.junit.jupiter.api.assertThrows
import ru.customExceptions.PostNotFoundException
import ru.data.Attachment.Photo
import ru.data.Comment
import ru.data.Likes
import ru.data.Post
import ru.data.Repost
import ru.service.WallService
import kotlin.reflect.KClass
import kotlin.test.*

class WallServiceTest {
    private val service = WallService()
    private val photoAtt =
        Photo(type = "Photo", id = 1, albumId = 2, ownerId = 3, userId = 4, text = "Photo description")
    private val comment = Comment(
        id = 0,
        fromId = 212,
        date = 442315,
        text = "Comment text",
        replyToUser = 39,
        replyToComment = 53,
        photoAtt
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
        comment = Comment(
            id = -2147483648,
            fromId = 212,
            date = 442315,
            text = "Comment text",
            replyToUser = 39,
            replyToComment = 53,
            photoAtt
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

    @Test()
    fun myShouldThrow() {
        assertThrows<PostNotFoundException> {
            service.createComment(comment)
        }
    }

//    @Test(expected = PostNotFoundException::class)
//    fun shouldThrow() {
//        service.createComment(comment)
//    }



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

