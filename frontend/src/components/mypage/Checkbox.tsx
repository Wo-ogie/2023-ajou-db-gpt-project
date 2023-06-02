import React, { useState } from "react";
import styled from "styled-components";
import { GrCheckbox, GrCheckboxSelected } from "react-icons/gr";
import { useSetRecoilState } from "recoil";
import { ChatInfoState } from "../../recoil/ChatInfo";

const Checkbox = () => {
  const setChatInfoState = useSetRecoilState(ChatInfoState);
  const [isClicked, setIsClicked] = useState<boolean>(false);

  const handleClickCheckbox = () => {
    setIsClicked((prev) => !prev);
    setChatInfoState((prev) => ({ ...prev, bookmarks: !isClicked }));
  };

  return (
    <CheckboxWrapper onClick={handleClickCheckbox}>
      {isClicked ? <GrCheckboxSelected /> : <GrCheckbox />}
      저장 질문 모아보기
    </CheckboxWrapper>
  );
};

const CheckboxWrapper = styled.div`
  display: flex;
  flex-direction: row;
  gap: 8px;
`;
export default Checkbox;
