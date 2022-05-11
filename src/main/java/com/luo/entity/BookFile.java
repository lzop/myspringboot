package com.luo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file_tb")
public class BookFile implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 2L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "book_id")
    private Integer bookId;

    @TableField(value = "path")
    private String path;

    @TableField(exist = false)
    private Book book;

}
