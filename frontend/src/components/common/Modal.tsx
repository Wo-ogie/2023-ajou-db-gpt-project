import { MouseEventHandler, ReactNode, useEffect } from "react";
import ReactDom from "react-dom";
import styled from "styled-components";

type ModalProps = {
  children: ReactNode; // 모달 내용
  isVisible: boolean; // 모달 보여주기 상태
  closeModal: () => void; // 모달 닫기 함수
};

const Modal = ({ children, isVisible, closeModal }: ModalProps) => {
  const modalRoot = typeof window !== "undefined" && document.getElementById("root");

  const handleCloseModal: MouseEventHandler<HTMLDivElement> = (e) => {
    if (e.currentTarget === e.target) {
      closeModal();
      e.stopPropagation();
    }
  };

  useEffect(() => {
    if (isVisible) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "";
    }
  }, [isVisible]);

  return isVisible
    ? ReactDom.createPortal(
        <ModalOverlay onClick={handleCloseModal}>{children}</ModalOverlay>,
        modalRoot as HTMLElement
      )
    : null;
};

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 200;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default Modal;
