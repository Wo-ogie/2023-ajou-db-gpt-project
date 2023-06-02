import styled from "styled-components";

export const ChatContainer = styled.div`
  width: 60vw;
  margin: auto;
  p {
    margin: 0;
  }
`;

export const Question = styled.span`
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

export const Answer = styled.div`
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
  .spinner {
    animation: rotate_spinner 3s linear infinite;
    display: flex;
    align-items: center;
  }
  @keyframes rotate_spinner {
    100% {
      transform: rotate(360deg);
    }
  }
  margin-bottom: 12px;
`;
