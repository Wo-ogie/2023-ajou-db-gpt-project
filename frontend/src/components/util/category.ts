export const categoryList = [
  { key: "전체", val: "" },
  { key: "과학/기술", val: "SCIENCE" },
  { key: "개발/코딩", val: "DEV" },
  { key: "역사", val: "HISTORY" },
  { key: "예술/문학", val: "ART" },
  { key: "사회과학", val: "SOCIAL_SCIENCE" },
  { key: "스포츠", val: "SPORTS" },
  { key: "여행/문화", val: "CULTURE" },
  { key: "건강", val: "HEALTH" },
  { key: "자연/환경", val: "NATURE" },
  { key: "기타", val: "ETC" },
];

export const categoryObj: { [key: string]: string } = {
  전체: "",
  "과학/기술": "SCIENCE",
  "개발/코딩": "DEV",
  역사: "HISTORY",
  "예술/문학": "ART",
  사회과학: "SOCIAL_SCIENCE",
  스포츠: "SPORTS",
  "여행/문화": "CULTURE",
  건강: "HEALTH",
  "자연/환경": "NATURE",
  기타: "ETC",
};

export const SortedVal: { [key: string]: string } = {
  최신순: "LATEST",
  "오래된 순": "OLDEST",
};
