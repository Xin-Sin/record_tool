package top.xinsin.dao;

import top.xinsin.pojo.Record;

import java.util.List;

/**
 * @author xinsin
 * Created On 2023/8/14 17:45
 * @version 1.0
 */
public interface RecordMapper {
    List<Record> findAll();
}
