CREATE TABLE `file_tag` (
`id`  varchar(32) NOT NULL ,
`file`  varchar(32) NULL COMMENT 'file.id' ,
`tag`  varchar(32) NULL COMMENT 'tag.id' ,
PRIMARY KEY (`id`)
)