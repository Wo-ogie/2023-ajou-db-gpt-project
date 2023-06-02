import React, { SetStateAction } from "react";
import styled from "styled-components";
import Modal from "../common/Modal";
import { categoryList } from "../util/category";

interface ModalProps {
  isShowModal: boolean;
  toggleModal: () => void;
  setCategory: React.Dispatch<SetStateAction<string>>;
}
const CategoryModal = ({ isShowModal, toggleModal, setCategory }: ModalProps) => {
  const handleClickCategory = (keyword: string) => {
    setCategory(keyword);
    toggleModal();
  };

  return (
    <Modal isVisible={isShowModal} closeModal={toggleModal}>
      <CategoryModalWrapper>
        <div className="title">질문 카테고리를 선택해주세요.</div>
        <ButtonWrapper>
          {categoryList.slice(1).map((keyword) => (
            <RadioButton
              key={keyword.key}
              onClick={() => handleClickCategory(keyword.key)}
            >
              {keyword.key}
            </RadioButton>
          ))}
        </ButtonWrapper>
      </CategoryModalWrapper>
    </Modal>
  );
};

const CategoryModalWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 32px;
  background-color: #ffffff;
  border-radius: 32px;
  padding: 48px;

  width: 320px;

  .title {
    font-size: 20px;
    color: #000000;
    font-weight: 600;
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 10px;
`;

const RadioButton = styled.button`
  border-radius: 16px;
  outline: none;
  border: 1px solid lightgray;
  background-color: white;
  font-size: 20px;
  padding: 8px 13px;
  cursor: pointer;
  &:hover {
    background-color: #aeadad;
    color: #ffffff;
  }
`;
export default CategoryModal;
