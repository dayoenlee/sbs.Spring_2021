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



CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    `authlevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '귄한레벨 (3=일반,7= 관리자)',
    `name` CHAR(20) NOT NULL,
    `nickname` CHAR(20) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0= 탈퇴전,1= 탈퇴)',
    delDate DATETIME COMMENT '탈퇴날짜'
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
#관리자회원 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId='admin',
loginPw ='admin',
`authlevel` =7,
`name` ='관리자',
`nickname` ='관리자',
cellphoneNo ='00012345678',
email ='abcdef@gmail.com';
#일반회원 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId='user1',
loginPw ='user1',
`name` ='사용자1',
`nickname` ='사용자1',
cellphoneNo ='00018765432',
email ='fedcba@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId='user2',
loginPw ='user2',
`name` ='사용자2',
`nickname` ='사용자2',
cellphoneNo ='00018765432',
email ='fedcba@gmail.com';

SELECT * FROM article;
SELECT * FROM `member`;

