import React from "react";
import { Button, HeaderContentWrapper } from "../chat/HeaderContent";
import { useNavigate } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import { ChatInfoState } from "../../recoil/ChatInfo";

const HeaderContent = () => {
  const navigate = useNavigate();
  const setChatInfoState = useSetRecoilState(ChatInfoState);
  const handleClickMain = () => {
    setChatInfoState({
      keyword: "",
      category: "",
      sort: "",
      bookmarks: false,
    });
    navigate("/");
  };
  const handleClickLogOut = () => {
    localStorage.clear();
    navigate("/");
  };
  return (
    <HeaderContentWrapper>
      <Button onClick={handleClickMain}>질문하기</Button>
      <Button onClick={handleClickLogOut}>로그아웃</Button>
    </HeaderContentWrapper>
  );
};

export default HeaderContent;
