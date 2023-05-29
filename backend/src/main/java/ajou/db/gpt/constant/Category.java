package ajou.db.gpt.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Schema(description = "<p>질문 카테고리. " +
        "<p>카테고리는 다음 항목들로 구성되어 있다." +
        "<ul>" +
        "<li><code>SCIENCE</code> - 과학/기술" +
        "<li><code>DEV</code> - 개발/코딩" +
        "<li><code>HISTORY</code> - 역사" +
        "<li><code>ART</code> - 예술/문학" +
        "<li><code>SOCIAL_SCIENCE</code> - 사회과학" +
        "<li><code>SPORTS</code> - 스포츠" +
        "<li><code>CULTURE</code> - 여행/문화" +
        "<li><code>HEALTH</code> - 건강" +
        "<li><code>NATURE</code> - 자연/환경" +
        "<li><code>ETC</code> - 기타" +
        "</ul>",
        example = "SCIENCE")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Category {

    SCIENCE("과학/기술"),
    DEV("개발/코딩"),
    HISTORY("역사"),
    ART("예술/문학"),
    SOCIAL_SCIENCE("사회과학"),
    SPORTS("스포츠"),
    CULTURE("여행/문화"),
    HEALTH("건강"),
    NATURE("자연/환경"),
    ETC("기타");

    private final String description;
}
