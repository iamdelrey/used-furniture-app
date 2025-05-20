package rksp.furniture.service

data class ConversationDto(
    val id: Long,
    val partnerName: String,
    val partnerAvatarUrl: String = "/css/images/default-avatar.png",
    val lastMessage: String,
    val lastTime: String,
    val unreadCount: Int = 0
)
