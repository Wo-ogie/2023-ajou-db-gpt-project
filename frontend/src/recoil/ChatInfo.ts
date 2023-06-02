import { atom } from "recoil";

export const ChatInfoState = atom({
  key: "ChatInfoState",
  default: {
    keyword: "",
    category: "",
    sort: "",
    bookmarks: false,
  },
});
