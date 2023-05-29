package ajou.db.gpt.controller;

import ajou.db.gpt.dto.chat.QuestionWithAnswerRes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatListRes {

    private List<QuestionWithAnswerRes> chats;
}
