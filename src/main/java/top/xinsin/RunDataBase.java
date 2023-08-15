package top.xinsin;

import com.alibaba.fastjson2.JSON;
import lombok.SneakyThrows;
import top.xinsin.pojo.Record;
import top.xinsin.util.BaseDao;
import top.xinsin.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author xinsin
 * Created On 2023/8/15 11:46
 * @version 1.0
 */
public class RunDataBase {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(BaseDao.selectForList("SELECT * from record", Record.class)));
    }
}
