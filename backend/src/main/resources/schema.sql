CREATE TABLE users
(
    user_id    VARCHAR(20)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(10)  NOT NULL,
    created_at DATETIME(6)  NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (user_id)
);

CREATE TABLE answer
(
    answer_id  INT            NOT NULL AUTO_INCREMENT,
    content    VARCHAR(10000) NOT NULL,
    created_at DATETIME(6)    NOT NULL,
    PRIMARY KEY (answer_id)
);

CREATE TABLE question
(
    question_id INT            NOT NULL AUTO_INCREMENT,
    user_id     VARCHAR(20)    NOT NULL,
    answer_id   INT            NOT NULL,
    category    VARCHAR(50)    NOT NULL,
    content     VARCHAR(10000) NOT NULL,
    created_at  DATETIME(6)    NOT NULL,
    PRIMARY KEY (question_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (answer_id) REFERENCES answer (answer_id)
);

CREATE TABLE bookmark
(
    bookmark_id INT         NOT NULL AUTO_INCREMENT,
    user_id     VARCHAR(20) NOT NULL,
    question_id INT         NOT NULL,
    created_at  DATETIME(6) NOT NULL,
    PRIMARY KEY (bookmark_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);
