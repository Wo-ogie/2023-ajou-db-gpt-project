import React from "react";
import styled from "styled-components";

const Header = ({ children }: { children: React.ReactNode }) => {
  return <HeaderWrapper>{children}</HeaderWrapper>;
};

const HeaderWrapper = styled.div`
  position: fixed;
  top: 0;
  width: 100%;
  background-color: aliceblue;
  z-index: 3;
`;
export default Header;
