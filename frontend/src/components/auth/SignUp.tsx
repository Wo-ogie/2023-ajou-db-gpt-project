import axios from "axios";
import React, { SetStateAction, useState } from "react";
import styled from "styled-components";

import { Button, Input, InputForm } from "./style";

const SignUp = ({
  setAuthState,
}: {
  setAuthState: React.Dispatch<SetStateAction<string>>;
}) => {
  const [name, setName] = useState<string>("");
  const [id, setId] = useState<string>("");
  const [pwd, setPwd] = useState<string>("");

  const handleChangeName = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setName(value);
  };

  const handleChangeId = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setId(value);
  };

  const handleChangePwd = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPwd(value);
  };

  const handleClickSignUp = (e: any) => {
    e.preventDefault();
    axios
      .post(
        `${process.env.REACT_APP_BASE_URL}/users`,
        {
          id,
          password: pwd,
          name,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then(() => {
        setAuthState("SignIn");
      })
      .catch((err) => console.log(err.response));
  };

  return (
    <SignUpWrapper>
      <div className="titleWrap">회원가입</div>
      <InputForm onSubmit={handleClickSignUp}>
        <Input
          type="text"
          placeholder="이름"
          value={name}
          onChange={handleChangeName}
        />
        <Input
          type="text"
          placeholder="아이디"
          value={id}
          onChange={handleChangeId}
        />
        <Input
          type="password"
          placeholder="비밀번호"
          value={pwd}
          onChange={handleChangePwd}
        />
        <Button
          type="submit"
          onSubmit={handleClickSignUp}
          className={
            name.length > 0 && id.length > 0 && pwd.length > 0
              ? "active"
              : "disabled"
          }
          disabled={name.length === 0 || id.length === 0 || pwd.length === 0}
        >
          회원가입
        </Button>
        <p onClick={() => setAuthState("SignIn")}>로그인</p>
      </InputForm>
    </SignUpWrapper>
  );
};

const SignUpWrapper = styled.div`
  .titleWrap {
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
    margin-top: 32px;
    margin-bottom: 16px;
    font-size: 18px;
  }
`;

export default SignUp;
