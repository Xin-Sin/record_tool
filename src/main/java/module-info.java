/**
 * @author xinsin
 * Created On 2023/8/14 16:17
 */

module record.tool {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires static lombok;
    requires org.xerial.sqlitejdbc;
    requires org.mybatis;
    requires com.alibaba.fastjson2;


    opens top.xinsin to javafx.fxml;
    exports top.xinsin;
    exports top.xinsin.pojo;
}