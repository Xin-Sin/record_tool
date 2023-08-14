package top.xinsin.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author xinsin
 * Created On 2023/8/14 17:37
 * @version 1.0
 */
@Data
public class Record {
    private Long id;
    private String title;
    private Integer status;
    private Date startDate;
    private Date finishDate;
    private String remark;
    private Double price;
    private Integer delFlag;
    private Date createDate;
    private Date updateDate;
}
