package ajou.db.gpt.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatListRes {

    private List<QuestionWithAnswerRes> chats;
}
