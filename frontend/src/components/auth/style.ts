import styled from "styled-components";

export const InputForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  margin-bottom: 10px;

  p {
    font-size: 12px;
    color: #5f5f5f;
    cursor: pointer;
  }
`;

export const Input = styled.input`
  height: 45px;
  border-radius: 5px;
  border: 1px solid #8d8d8d;
  color: #8d8d8d;
  background-color: #fafafa;
  margin-bottom: 8px;
  padding: 0 10px;
  font-size: 13px;
  outline: none;
`;

export const Button = styled.button`
  height: 45px;
  border-radius: 5px;
  border: 1px solid #9f9f9f;
  background-color: #cdcdcd;
  color: #333333;
  margin-bottom: 8px;
  margin-top: 10px;
  font-size: 14px;

  cursor: pointer;

  &:focus {
    outline: none;
  }
  .active {
    background-color: #9f9f9f;
    color: #ffffff;
    font-weight: bold;
  }
  .disabled {
    background-color: #cdcdcd;
  }
`;
