CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `city` varchar(100) NOT NULL DEFAULT '' COMMENT '商品产地',
  `price` int(11) NOT NULL DEFAULT 0 COMMENT '商品价格',
  `number` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
  `picture` varchar(100) NOT NULL DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
