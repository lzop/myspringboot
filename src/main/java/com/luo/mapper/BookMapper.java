package com.luo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luo.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
