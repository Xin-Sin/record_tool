package top.xinsin.service;

import org.apache.ibatis.session.SqlSession;
import top.xinsin.dao.RecordMapper;
import top.xinsin.pojo.Record;

import java.util.List;

/**
 * @author xinsin
 * Created On 2023/8/14 17:53
 * @version 1.0
 */
public class RecordService implements RecordMapper {
    @Override
    public List<Record> findAll() {
        return null;
    }
}
