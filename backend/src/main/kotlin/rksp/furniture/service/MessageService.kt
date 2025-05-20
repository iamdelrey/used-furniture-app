package rksp.furniture.service

import org.springframework.stereotype.Service

@Service
class MessageService {
    fun getConversationsForUser(username: String): List<ConversationDto> {
        return listOf(
            ConversationDto(1, "Иван Иванов", "/css/images/default-avatar.png",
                "Привет, интересует стол", "14:22", 1),
            ConversationDto(2, "Ольга Петрова", "/css/images/default-avatar.png",
                "Да, завтра привезу", "11:05", 0)
        )
    }
}
