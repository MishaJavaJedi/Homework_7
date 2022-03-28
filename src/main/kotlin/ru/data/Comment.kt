package ru.data

import ru.data.Attachment.Attachment

data class Comment(
   val postId:Int,
   val id:Int,
   val fromId:Int,
   val date:Int,
   val text:String,
   val replyToUser:Int,
   val replyToComment:Int,
   val attachments: Attachment
)


