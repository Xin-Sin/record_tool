package top.xinsin.service;

import org.apache.ibatis.session.SqlSession;
import top.xinsin.dao.RecordMapper;
import top.xinsin.pojo.Record;

import java.io.IOException;
import java.util.List;

import static top.xinsin.util.MyBatisUtil.getSqlSession;

/**
 * @author xinsin
 * Created On 2023/8/14 17:53
 * @version 1.0
 */
public class RecordService implements RecordMapper {
    private final RecordMapper recordMapper;
    @Override
    public List<Record> findAll() {
        return recordMapper.findAll();
    }

    {
        try {
            recordMapper = getSqlSession().getMapper(RecordMapper.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
