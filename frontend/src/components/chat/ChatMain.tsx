import React, { useState } from "react";
import styled from "styled-components";
import { ContentsType } from "./ChatTemplate";
import { BsBookmark, BsBookmarkFill } from "react-icons/bs";
import axios from "axios";
import { Answer, ChatContainer, Question } from "./style";
import gptIcon from "../../assets/db-gpt.png";
import { ReactMarkdown } from "react-markdown/lib/react-markdown";

export const renderers = {
  code: ({ children }: any) => (
    <div style={{ overflowX: "auto" }}>
      <p>{children}</p>
    </div>
  ),
};

const ChatMain = ({
  chat,
}: {
  chat: ContentsType;
  isLoading: boolean;
  message: string;
}) => {
  const [isClicked, setIsClicked] = useState(false);

  const handleClickBookmark = (questionId: number) => {
    setIsClicked((prev) => !prev);
    if (!isClicked) {
      axios
        .post(
          `${process.env.REACT_APP_BASE_URL}/bookmarks/questions/${questionId}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            },
          }
        )
        .then(({ data }) => console.log(data));
    } else {
      axios
        .delete(
          `${process.env.REACT_APP_BASE_URL}/bookmarks/questions/${questionId}`,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            },
          }
        )
        .then(({ data }) => console.log(data));
    }
  };
  return (
    <ChatMainWrapper>
      <ChatContainer>
        <Question>
          <span>{chat.question.content}</span>
          {isClicked ? (
            <BsBookmarkFill
              style={{
                cursor: "pointer",
              }}
              onClick={() => handleClickBookmark(chat.question.id)}
            />
          ) : (
            <BsBookmark
              style={{
                cursor: "pointer",
              }}
              onClick={() => handleClickBookmark(chat.question.id)}
            />
          )}
        </Question>
        <Answer>
          <img src={gptIcon} alt="gpt 아이콘" width={28} height={28} />
          <div>
            <ReactMarkdown components={renderers}>{chat.answer}</ReactMarkdown>
          </div>
        </Answer>
      </ChatContainer>
    </ChatMainWrapper>
  );
};

const ChatMainWrapper = styled.div``;

export default ChatMain;
