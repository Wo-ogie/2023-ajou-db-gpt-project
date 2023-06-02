import React, { useState } from "react";
import styled from "styled-components";
import { BsBookmark, BsBookmarkFill } from "react-icons/bs";
import axios from "axios";
import gptIcon from "../../assets/db-gpt.png";
import { ReactMarkdown } from "react-markdown/lib/react-markdown";
import { renderers } from "../chat/ChatMain";

const ChatList = ({ chat }: { chat: any }) => {
  const [isClicked, setIsClicked] = useState<boolean>(chat.question.isMarked);

  const handleClickBookmark = (questionId: number) => {
    setIsClicked(true);
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
  };

  const handleDeleteBookmark = (questionId: number) => {
    setIsClicked(false);
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
  };

  return (
    <ChatListWrapper>
      <ChatContainer key={chat.question.id}>
        <Question>
          <span>{chat.question.content}</span>
          {isClicked ? (
            <BsBookmarkFill
              style={{
                cursor: "pointer",
              }}
              onClick={() => handleDeleteBookmark(chat.question.id)}
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
    </ChatListWrapper>
  );
};

const ChatListWrapper = styled.div`
  width: 60vw;
  margin: 0 auto;
  p {
    margin: 0;
  }
`;
const ChatContainer = styled.div``;
const Question = styled.span`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 8px;
  span {
    display: inline-block;
    padding: 6px 12px;
    background-color: aliceblue;
    border-radius: 8px;
  }
  margin-bottom: 12px;
`;

const Answer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  gap: 8px;
  div {
    display: inline-block;
    padding: 6px 12px;
    background-color: #e7e7e7;
    border-radius: 8px;
    max-width: 70%;
  }
  margin-bottom: 12px;
`;
export default ChatList;
