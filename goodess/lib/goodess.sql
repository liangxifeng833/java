CREATE TABLE `goodess` (
  `id` int(11) NOT NULL auto_increment,
  `user_name` varchar(50) not null default '',
  `sex` tinyint(1) not null default '0',
  `birthday` date not null default '0000-00-00',
  `add_time` datetime not null default '0000-00-00 00:00:00',
  `update_time` datetime not null default '0000-00-00 00:00:00',	
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
