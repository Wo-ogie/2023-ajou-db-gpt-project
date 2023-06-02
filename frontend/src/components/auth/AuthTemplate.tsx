import React, { useState } from "react";
import styled from "styled-components";
import SignUp from "./SignUp";
import SignIn from "./SignIn";

const AuthTemplate = () => {
  // deafult: 로그인
  const [authState, setAuthState] = useState<string>("SignIn");
  return (
    <AuthWrapper>
      {authState === "SignUp" ? (
        <SignUp setAuthState={setAuthState} />
      ) : (
        <SignIn setAuthState={setAuthState} />
      )}
    </AuthWrapper>
  );
};

const AuthWrapper = styled.div`
  width: 40vw;
  margin: 50px auto;
  padding: 16px 32px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  text-align: center;
`;
export default AuthTemplate;
