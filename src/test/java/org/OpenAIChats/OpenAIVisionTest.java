package org.OpenAIChats;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OpenAIVisionTest {
        private final String apiKey = System.getenv("OPENAI_API_KEY");
    OpenAiChatModel chatModel = OpenAiChatModel.builder()
            .apiKey(apiKey)
            .modelName("gpt-4o-mini")
            .build();

    @Test
    void visionChat() {
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Largo_do_Pelourinho_Salvador_2019-9754_%28cropped%29.jpg/640px-Largo_do_Pelourinho_Salvador_2019-9754_%28cropped%29.jpg";
        Response<AiMessage> response = chatModel.generate(
                UserMessage.from(
                        ImageContent.from(imageUrl),
                        TextContent.from("Describe what is going on in this picture and list all of the items that are blue. Also, describe all of the items that are not blue in the picture and list them.")
                )
        );
        System.out.println(response.content().text());
    }
}
