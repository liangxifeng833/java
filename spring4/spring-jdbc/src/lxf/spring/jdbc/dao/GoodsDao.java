package lxf.spring.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lxf.spring.jdbc.bean.Goods;
/**
 * GoodsDao操作数据库
 * @author lxf
 *
 */
@Repository
public class GoodsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 根据主键获取goods表单条记录
     * @param id
     * @return
     */
    public Goods getOne(Integer id)
    {
        String sql = "SELECT *  FROM  goods WHERE goods_id = ?";
        RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<>(Goods.class);
        Goods goods=null;
        try {
            goods = jdbcTemplate.queryForObject(sql, rowMapper,id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return goods;
    }
}
