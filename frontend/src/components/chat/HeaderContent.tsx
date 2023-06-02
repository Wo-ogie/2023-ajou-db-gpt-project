import React from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";

const HeaderContent = ({
  toggleModal,
  category,
}: {
  toggleModal: () => void;
  category: string;
}) => {
  const navigate = useNavigate();
  const handleClickLogOut = () => {
    localStorage.clear();
    navigate("/auth");
  };
  return (
    <HeaderContentWrapper>
      <div onClick={toggleModal} className="category">
        {category ? <p> 카테고리 : {category}</p> : "카테고리 선택"}
      </div>
      <ButtonWrapper>
        <Link to="/mypage">
          <Button>마이페이지</Button>
        </Link>
        <Button onClick={handleClickLogOut}>로그아웃</Button>
      </ButtonWrapper>
    </HeaderContentWrapper>
  );
};

export const HeaderContentWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin: 12px 20px;
  height: 100%;

  .category {
    display: flex;
    align-items: center;
    cursor: pointer;
    padding: 0 16px;
    height: 40px;
    border-radius: 16px;
    background-color: #aeadad;
    color: #ffffff;
    font-size: 15px;
    font-weight: 600;
    text-align: center;
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  gap: 8px;
`;

export const Button = styled.button`
  padding: 1px 10px;
  margin: 0 6px;
  height: 40px;
  border-radius: 8px;
  border: 1px solid #9f9f9f;
  background-color: #edecec;
  color: #333333;
  font-size: 14px;
  cursor: pointer;
`;
export default HeaderContent;
