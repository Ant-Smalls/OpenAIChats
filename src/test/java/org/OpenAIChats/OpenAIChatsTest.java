package org.OpenAIChats;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenAIChatsTest {
    private final String apiKey = System.getenv("OPENAI_API_KEY");

    @Test
    void chat() {
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .build();
        ChatResponse response = chatModel.chat(ChatRequest.builder()
                .messages(List.of(
                        new UserMessage("""
                    If different types of rocks had unique personalities, which one would be the life of the party, and what would their favorite dance move be?""")))
                .build());
        System.out.println(response.aiMessage().text().replaceAll("([.!?])\\s","$1\n"));
    }
}
