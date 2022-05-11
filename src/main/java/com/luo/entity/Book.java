package com.luo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "book")
public class Book implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "book_name")
    private String bookName;

    @TableField(value = "book_summary")
    private String bookSummary;

    @TableField(value = "author")
    private String author;

    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private List<BookFile> bookFileList;

}
