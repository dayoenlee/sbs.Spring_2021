#데아타베이스 생성
DROP DATABASE IF EXISTS sbs_s_2021_10;
CREATE DATABASE sbs_s_2021_10;
USE sbs_s_2021_10;
#테이블 생성
CREATE TABLE article(
    id INT (10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title ='제목',
`body` = '내용';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title ='제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title ='제목3',
`body` = '내용3';
