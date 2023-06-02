import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useModal from "../../hooks/useModal";
import styled from "styled-components";
import CategoryModal from "./CategoryModal";
import Header from "../common/Header";
import HeaderContent from "./HeaderContent";

import { FiSend } from "react-icons/fi";
import axios from "axios";
import { categoryObj } from "../util/category";
import ChatMain from "./ChatMain";
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import { Answer, ChatContainer, Question } from "./style";
import gptIcon from "../../assets/db-gpt.png";

export interface ContentsType {
  question: {
    id: number;
    category: string;
    content: string;
  };
  answer: string;
}
const ChatTemplate = () => {
  const navigate = useNavigate();
  const { isShowModal, openModal, toggleModal } = useModal();

  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [category, setCategory] = useState<string>("");
  const [message, setMessage] = useState<string>("");
  const [chatContents, setChatContents] = useState<ContentsType[]>([]);

  useEffect(() => {
    if (!localStorage.getItem("accessToken")) {
      navigate("/auth");
    } else {
      if (category === "") {
        openModal();
      }
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleChangeMessage = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setMessage(value);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    if (category !== "") {
      setIsLoading(true);
      axios
        .post(
          `${process.env.REACT_APP_BASE_URL}/chats`,
          {
            category: categoryObj[`${category}`],
            content: message,
          },
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            },
          }
        )
        .then(({ data }) => {
          setIsLoading(false);
          setMessage("");
          setChatContents([...chatContents, data]);
        })
        .catch((err) => console.log(err.response));
    } else {
      alert("카테고리를 선택해주세요.");
    }
  };

  return (
    <>
      <Header
        children={<HeaderContent toggleModal={toggleModal} category={category} />}
      />
      <ChatWrapper>
        <TopWrap>
          <form onSubmit={handleSubmit}>
            <Input
              type="text"
              placeholder="send a message"
              value={isLoading ? "" : message}
              onChange={handleChangeMessage}
            />
            <FiSend
              className="send-button"
              style={{
                color: message.length > 0 ? "rgba(69,89,164,.5)" : "#d9d9e3",
                cursor: "pointer",
              }}
              onClick={handleSubmit}
            />
          </form>
        </TopWrap>
        <MainWrap>
          {chatContents.map((chat, idx) => (
            <ChatMain
              key={idx}
              chat={chat}
              isLoading={isLoading}
              message={message}
            />
          ))}
          {isLoading ? (
            <ChatContainer>
              <Question>
                <span>{message}</span>
              </Question>
              <Answer>
                <img src={gptIcon} alt="gpt 아이콘" width={28} height={28} />
                <AiOutlineLoading3Quarters className="spinner" size={18} />
              </Answer>
            </ChatContainer>
          ) : null}
        </MainWrap>
        <CategoryModal
          isShowModal={isShowModal}
          toggleModal={toggleModal}
          setCategory={setCategory}
        />
      </ChatWrapper>
    </>
  );
};

const ChatWrapper = styled.div`
  margin: 80px 20px 50px;
`;

const TopWrap = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
  form {
    position: relative;
    .send-button {
      position: absolute;
      right: 20px;
      top: 30px;
    }
  }
`;
const MainWrap = styled.div``;
const Input = styled.input`
  height: 45px;
  width: 60vw;
  border-radius: 5px;
  border: 1px solid #8d8d8d;
  color: #000000;
  background-color: #ffffff;
  margin-top: 16px;
  padding: 0 10px;
  font-size: 13px;
  outline: none;
`;

export default ChatTemplate;
