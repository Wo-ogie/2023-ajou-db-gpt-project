package ajou.db.gpt.dto.chat;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GptRes {

    private String id;
    private String object;
    private Integer created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    public String getAnswer() {
        return this.getChoices().get(0).getMessage().getContent();
    }

    @Getter
    private static class Usage {
        @SerializedName("prompt_tokens")
        private Integer promptTokens;

        @SerializedName("completion_tokens")
        private Integer completionTokens;

        @SerializedName("total_tokens")
        private Integer totalTokens;
    }

    @Getter
    private static class Choice {

        private Message message;

        @SerializedName("finish_reason")
        private String finishReason;

        private Integer index;

        @Getter
        private static class Message {
            private String role;
            private String content;
        }
    }
}
