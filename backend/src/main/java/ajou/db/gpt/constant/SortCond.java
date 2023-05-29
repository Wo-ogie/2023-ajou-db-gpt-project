package ajou.db.gpt.constant;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "<p>정렬 기준. 항목은 다음고 같다." +
        "<ul>" +
        "<li>LATEST - 최신순" +
        "<li>OLDEST - 오래된순" +
        "</ul>",
        example = "LATEST")
public enum SortCond {

    LATEST, OLDEST
}
