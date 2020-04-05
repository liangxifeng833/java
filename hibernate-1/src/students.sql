CREATE TABLE `grade` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(20) NULL,
  `gdesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `student2` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `gid` INT DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE student2 add   constraint fk_student_gid foreign key(gid) REFERENCES grade (gid);