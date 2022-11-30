CREATE TABLE `nhn_academy_12`.`BoardUserRole`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `nhn_academy_12`.BoardUser
(
    `id`        INT         NOT NULL,
    `user_name` VARCHAR(20) NOT NULL,
    `password`  VARCHAR(30) NOT NULL,
    `role`      INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE,
    INDEX       `id_idx` (`role` ASC) VISIBLE,
    CONSTRAINT `role_id`
        FOREIGN KEY (`role`)
            REFERENCES `nhn_academy_12`.`BoardUserRole` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `nhn_academy_12`.`BoardUser`
    CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT;

CREATE TABLE `nhn_academy_12`.`BoardReply`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `content`    VARCHAR(45) NOT NULL,
    `post_id`    INT         NOT NULL,
    `created_at` DATETIME    NOT NULL,
    `visibility` TINYINT     NOT NULL COMMENT 'Tinting',
    PRIMARY KEY (`id`)
);

CREATE TABLE `nhn_academy_12`.`BoardPost`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(100) NOT NULL,
    `content`     VARCHAR(600) NOT NULL,
    `writed_by`   INT          NOT NULL,
    `modified_by` INT NULL,
    `created_at`  DATETIME     NOT NULL,
    `modified_at` DATETIME NULL,
    `visibility`  TINYINT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX         `writed_by_idx` (`writed_by` ASC) VISIBLE,
    INDEX         `modified_by_idx` (`modified_by` ASC) VISIBLE,
    CONSTRAINT `writed_by`
        FOREIGN KEY (`writed_by`)
            REFERENCES `nhn_academy_12`.`BoardUser` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `modified_by`
        FOREIGN KEY (`modified_by`)
            REFERENCES `nhn_academy_12`.`BoardUser` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `nhn_academy_12`.`BoardReply`
    ADD INDEX `post_id_idx` (`post_id` ASC) VISIBLE;
;
ALTER TABLE `nhn_academy_12`.`BoardReply`
    ADD CONSTRAINT `post_id`
        FOREIGN KEY (`post_id`)
            REFERENCES `nhn_academy_12`.`BoardPost` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `nhn_academy_12`.`BoardPost`
    CHANGE COLUMN `created_at` `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
    CHANGE COLUMN `modified_at` `modified_at` TIMESTAMP NULL DEFAULT current_timestamp,
    CHANGE COLUMN `visibility` `visibility` TINYINT NOT NULL DEFAULT 1;

ALTER TABLE `nhn_academy_12`.`BoardReply`
    CHANGE COLUMN `created_at` `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
    CHANGE COLUMN `visibility` `visibility` TINYINT NOT NULL DEFAULT 1;

ALTER TABLE `nhn_academy_12`.`BoardReply`
    ADD COLUMN `writer` INT NOT NULL AFTER `visibility`,
CHANGE COLUMN `content` `content` VARCHAR(300) NOT NULL ,
ADD INDEX `writer_id_idx` (`writer` ASC) VISIBLE;
;
ALTER TABLE `nhn_academy_12`.`BoardReply`
    ADD CONSTRAINT `writer_id`
        FOREIGN KEY (`writer`)
            REFERENCES `nhn_academy_12`.`BoardUser` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `nhn_academy_12`.`BoardPost`
DROP
FOREIGN KEY `modified_by`,
DROP
FOREIGN KEY `writed_by`;
ALTER TABLE `nhn_academy_12`.`BoardPost`
    CHANGE COLUMN `writed_by` `writer` INT NOT NULL,
    CHANGE COLUMN `modified_by` `modifier` INT NULL DEFAULT NULL;
ALTER TABLE `nhn_academy_12`.`BoardPost`
    ADD CONSTRAINT `modified_by`
        FOREIGN KEY (`modifier`)
            REFERENCES `nhn_academy_12`.`BoardUser` (`id`),
ADD CONSTRAINT `writed_by`
FOREIGN KEY (`writer`)
REFERENCES `nhn_academy_12`.`BoardUser` (`id`);

ALTER TABLE `nhn_academy_12`.`BoardPost`
DROP
COLUMN `modified_at`;

ALTER TABLE `nhn_academy_12`.`BoardReply`
    RENAME TO `nhn_academy_12`.`BoardComment`;

ALTER TABLE `nhn_academy_12`.`BoardPost`
    ADD COLUMN `modified_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `created_at`;

ALTER TABLE `nhn_academy_12`.`BoardUser`
DROP
FOREIGN KEY `role_id`;
ALTER TABLE `nhn_academy_12`.`BoardUser`
    CHANGE COLUMN `role` `role` INT NOT NULL DEFAULT 2;
ALTER TABLE `nhn_academy_12`.`BoardUser`
    ADD CONSTRAINT `role_id`
        FOREIGN KEY (`role`)
            REFERENCES `nhn_academy_12`.`BoardUserRole` (`id`);

ALTER TABLE `nhn_academy_12`.`BoardPost`
    CHANGE COLUMN `modified_at` `modified_at` TIMESTAMP NULL;

ALTER TABLE `nhn_academy_12`.`BoardComment`
    CHANGE COLUMN `writer` `writer` INT NOT NULL AFTER `post_id`;

ALTER TABLE `nhn_academy_12`.`BoardPost`
DROP
FOREIGN KEY `modified_by`,
DROP
FOREIGN KEY `writed_by`;
ALTER TABLE `nhn_academy_12`.`BoardPost`
    CHANGE COLUMN `writer` `created_by` INT NOT NULL,
    CHANGE COLUMN `modifier` `updated_by` INT NULL DEFAULT NULL,
    CHANGE COLUMN `modified_at` `updated_at` TIMESTAMP NULL DEFAULT NULL;
ALTER TABLE `nhn_academy_12`.`BoardPost`
    ADD CONSTRAINT `modified_by`
        FOREIGN KEY (`updated_by`)
            REFERENCES `nhn_academy_12`.`BoardUser` (`id`),
ADD CONSTRAINT `writed_by`
FOREIGN KEY (`created_by`)
REFERENCES `nhn_academy_12`.`BoardUser` (`id`);

CREATE TABLE `nhn_academy_12`.`BoardLike`
(
    `post_id` INT     NOT NULL,
    `user_id` INT     NOT NULL,
    `liked`   TINYINT NOT NULL,
    PRIMARY KEY (`post_id`, `user_id`),
    INDEX     `like_user_id_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `like_post_id`
        FOREIGN KEY (`post_id`)
            REFERENCES `nhn_academy_12`.`BoardPost` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `like_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `nhn_academy_12`.`BoardUser` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);