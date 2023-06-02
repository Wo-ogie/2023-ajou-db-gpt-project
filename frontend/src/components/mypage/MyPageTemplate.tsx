import React, { useEffect, useState } from "react";
import Header from "../common/Header";
import HeaderContent from "./HeaderContent";
import styled from "styled-components";
import { SortedVal, categoryList, categoryObj } from "../util/category";

import KeywordInput from "./KeywordInput";
import axios from "axios";
import ChatList from "./ChatList";
import { BiDownArrow } from "react-icons/bi";
import Checkbox from "./Checkbox";
import { useRecoilState } from "recoil";
import { ChatInfoState } from "../../recoil/ChatInfo";
import { useNavigate } from "react-router-dom";
const MyPageTemplate = () => {
  const navigate = useNavigate();

  const [chatInfoState, setChatInfoState] = useRecoilState(ChatInfoState);
  const [isClicked, setIsClicked] = useState<boolean>(false);
  const [isClickedSorted, setIsClickedSorted] = useState<boolean>(false);
  const [chatContents, setChatContents] = useState<any>([]);

  useEffect(() => {
    if (!localStorage.getItem("accessToken")) {
      navigate("/auth");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    const category = chatInfoState.category
      ? categoryObj[`${chatInfoState.category}`]
      : "";
    const keyword = chatInfoState.keyword ? chatInfoState.keyword : "";
    const sort = chatInfoState.sort ? SortedVal[`${chatInfoState.sort}`] : "";

    axios
      .get(
        `${process.env.REACT_APP_BASE_URL}/chats?category=${category}&keyword=${keyword}&sort=${sort}&onlyMarked=${chatInfoState.bookmarks}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          },
        }
      )
      .then(({ data }) => setChatContents(data.chats))
      .catch((err) => console.log(err.response));
  }, [chatInfoState]);

  const handleClickDropdown = () => {
    setIsClicked((prev) => !prev);
  };

  const handleClickSortedDropdown = () => {
    setIsClickedSorted((prev) => !prev);
  };

  const handleClickCategory = (keyword: string) => {
    setIsClicked((prev) => !prev);
    setChatInfoState((prev) => ({ ...prev, category: keyword }));
  };

  const handleClickSort = (keyword: string) => {
    setIsClickedSorted((prev) => !prev);
    setChatInfoState((prev) => ({ ...prev, sort: keyword }));
  };

  return (
    <>
      <Header children={<HeaderContent />} />
      <MyPageWrapper>
        <TopWrap>
          <KeywordInput />
          <Checkbox />
          <Dropdown>
            <div onClick={handleClickDropdown} className="top">
              {chatInfoState.category ? chatInfoState.category : "전체"}
              <BiDownArrow className={isClicked ? "clicked" : "basic"} />
            </div>
            {isClicked && (
              <div className="dropdown-menu">
                <ul>
                  {categoryList.slice(0, 10).map((keyword, idx) => (
                    <li
                      key={keyword.key}
                      onClick={() => handleClickCategory(keyword.key)}
                    >
                      {keyword.key}
                    </li>
                  ))}
                </ul>
              </div>
            )}
          </Dropdown>
          <Dropdown>
            <div onClick={handleClickSortedDropdown} className="top">
              {chatInfoState.sort ? chatInfoState.sort : "정렬"}
              <BiDownArrow
                className={isClickedSorted ? "clicked-sorted" : "basic"}
              />
            </div>
            {isClickedSorted && (
              <div className="dropdown-menu">
                <ul>
                  {["최신순", "오래된 순"].map((keyword) => (
                    <li key={keyword} onClick={() => handleClickSort(keyword)}>
                      {keyword}
                    </li>
                  ))}
                </ul>
              </div>
            )}
          </Dropdown>
        </TopWrap>
        <MainWrap>
          {chatContents.map((chat: any) => (
            <ChatList key={chat.question.id} chat={chat} />
          ))}
        </MainWrap>
      </MyPageWrapper>
    </>
  );
};

const MyPageWrapper = styled.div`
  margin: 80px 20px 50px;
  display: flex;
  flex-direction: row;
  gap: 20px;
`;
const TopWrap = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px;
`;

const Dropdown = styled.div`
  width: 100px;
  .top {
    cursor: pointer;
    white-space: nowrap;
    display: flex;
    flex-direction: row;
    gap: 6px;
    align-items: center;
  }

  .dropdown-menu {
    border: 1px solid lightgray;
    padding-left: 10px;
    ul {
      padding-left: 0;
      li {
        margin-bottom: 5px;
        list-style: none;
        cursor: pointer;
      }
    }
  }

  .clicked,
  .clicked-sorted {
    transform: rotate(180deg);
  }
`;

const MainWrap = styled.div``;
export default MyPageTemplate;
