import axios from "axios";
import React, { SetStateAction, useState } from "react";
import styled from "styled-components";

import { useNavigate } from "react-router-dom";
import { Button, Input, InputForm } from "./style";

const SignIn = ({
  setAuthState,
}: {
  setAuthState: React.Dispatch<SetStateAction<string>>;
}) => {
  const navigate = useNavigate();
  const [id, setId] = useState<string>("");
  const [pwd, setPwd] = useState<string>("");

  const handleChangeId = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setId(value);
  };

  const handleChangePwd = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setPwd(value);
  };

  const handleClickSignIn = (e: any) => {
    e.preventDefault();
    axios
      .post(
        `${process.env.REACT_APP_BASE_URL}/auth/login`,
        {
          id: id,
          password: pwd,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then(({ data }) => {
        localStorage.setItem("accessToken", data.accessToken);
        navigate("/");
      })
      .catch((err) => console.log(err.response));
  };

  return (
    <SignInWrapper>
      <div className="titleWrap">로그인</div>
      <InputForm onSubmit={handleClickSignIn}>
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
          onSubmit={handleClickSignIn}
          className={id.length > 0 && pwd.length > 0 ? "active" : "disabled"}
          disabled={id.length === 0 || pwd.length === 0}
        >
          로그인
        </Button>
        <p onClick={() => setAuthState("SignUp")}>회원가입</p>
      </InputForm>
    </SignInWrapper>
  );
};

const SignInWrapper = styled.div`
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

export default SignIn;
