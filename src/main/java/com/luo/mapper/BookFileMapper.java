package com.luo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luo.entity.BookFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookFileMapper extends BaseMapper<BookFile> {

    BookFile selectBookFile(@Param("id") Integer id);

}
