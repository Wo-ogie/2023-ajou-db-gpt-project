import React, { useState } from "react";
import { AiOutlineSearch } from "react-icons/ai";
import { useSetRecoilState } from "recoil";
import styled from "styled-components";
import { ChatInfoState } from "../../recoil/ChatInfo";

const KeywordInput = () => {
  const setChatInfoState = useSetRecoilState(ChatInfoState);
  const [keyword, setKeyword] = useState<string>("");
  const handleChangeKeyword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setKeyword(value);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    setChatInfoState((prev) => ({ ...prev, keyword: keyword }));
  };
  return (
    <KeywordInputWrapper>
      <form onSubmit={handleSubmit}>
        <Input
          type="text"
          placeholder="키워드 검색"
          value={keyword}
          onChange={handleChangeKeyword}
        />
        <AiOutlineSearch
          className="send-button"
          style={{
            color: keyword.length > 0 ? "rgba(69,89,164,.5)" : "#d9d9e3",
            cursor: "pointer",
          }}
          onClick={handleSubmit}
          size={20}
        />
      </form>
    </KeywordInputWrapper>
  );
};

const KeywordInputWrapper = styled.div`
  form {
    position: relative;
    .send-button {
      position: absolute;
      right: 20px;
      top: 15px;
    }
  }
`;
const Input = styled.input`
  height: 45px;
  width: 200px;
  border: none;
  border-bottom: 1px solid #8d8d8d;
  color: #000000;
  background-color: #ffffff;
  margin-bottom: 8px;
  padding: 0 10px;
  font-size: 13px;
  outline: none;
`;
export default KeywordInput;
