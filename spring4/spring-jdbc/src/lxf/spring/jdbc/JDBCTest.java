package lxf.spring.jdbc;
/**
 * 测试jdbc操作
 * @author lxf
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import lxf.spring.jdbc.bean.Goods;
import lxf.spring.jdbc.dao.GoodsDao;

public class JDBCTest {
    private ApplicationContext  ctx= null;
    //jdbcTemplate句柄
    private JdbcTemplate jdbcTemplate;
    //具名参数jdbcTemplate
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
    }
    /**
     * 使用具名参数时，可以使用update(String sql, SqlParameterSource paramSource)方法进行更新操作
     * １．SQL语句中的参数名和类的属性名保持一致
     * ２．使用SqlParameterSource接口的实现类BeanPropertySqlParameterSource作为参数
     */
    @Test
    public void testNameParamJdbc2()
    {
        String sql = "INSERT INTO goods  (goods_name,cost_price,selling_price,manufactuer) " + 
                            "VALUES (:goods_name,:cost_price,:selling_price,:manufactuer)";
        Goods goods = new Goods();
        goods.setGoods_name("耐克运动鞋");
        goods.setCost_price(800);
        goods.setSelling_price(699);
        goods.setManufactuer("产地山东");
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(goods);
        int res = namedParameterJdbcTemplate.update(sql, paramSource);
        System.out.println(res);
    }
    
    /**
     * 测试jdbc具名参数的新增数据库操作
     * 好处：若有多个参数则不用在去对应位置，直接对应参数名，便于维护
     */
    @Test
    public void testNameParamJdbc()
    {
        String sql = "INSERT INTO goods  (goods_name,cost_price,selling_price,manufactuer) VALUES (:gname,:cprice,:sprice,:manf)";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("gname", "阿迪达斯运动鞋");
        paramMap.put("cprice", "500");
        paramMap.put("sprice", "459");
        paramMap.put("manf", "产地河北");
        int res = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println(res);
    }
    /**
     * 获取单个列值，或做统计查询
     */
    @Test
    public void testQueryTotal()
    {
        String sql = "SELECT COUNT(goods_id) FROM goods";
        long count = jdbcTemplate.queryForObject(sql,long.class);
        System.out.println(count);
    }
    /**
     * 查到实体类的集合
     */
    @Test
    public void testQueryForList()
    {
        String sql = "SELECT * FROM goods WHERE goods_id > ?";
        RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<>(Goods.class);
        List<Goods> goods = jdbcTemplate.query(sql, rowMapper,1);
        System.out.println(goods);      
        
    }   
    /**
     * 从数据库获取一条记录,实际得到对应的对象
     */
    @Test
    public void testQueryForObject()
    {
        String sql = "SELECT *  FROM  goods WHERE goods_id = ?";
        RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<>(Goods.class);
        Goods goods = jdbcTemplate.queryForObject(sql, rowMapper,1);
        System.out.println(goods);      
    }
    
    /**
     * 执行批量更新，批量insert update delete
     */
    @Test
    public void testBatchUpate()
    {
        String sql = "INSERT INTO goods (goods_name,cost_price,selling_price,manufactuer) VALUES (?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        batchArgs.add(new Object[]{"AA","100","101","shanghai"});
        batchArgs.add(new Object[]{"BB","200","201","beijing"});
        batchArgs.add(new Object[]{"CC","300","301","guangzhou"});
        int[] res = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.asList(res));
    }
    /**
     * 修改数据
     */
    @Test
    public void testUpdate()
    {
        String sql = "UPDATE goods SET goods_name = ? WHERE goods_id = ?";
        jdbcTemplate.update(sql,"aaa",1);
    }   
    /**
     * 测试获取数据库连接
     * @throws SQLException 
     */
    @Test
    public void testDataSource() throws SQLException
    {
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
            System.out.println(dataSource.getConnection());
    }
    /**
     * 单元测试dao层查询单条记录
     */
    @Test
    public void testGetOne()
    {
        GoodsDao gD = (GoodsDao) ctx.getBean("goodsDao");
        Goods goodsOne = gD.getOne(1);
        System.out.println(goodsOne);
    }
}
