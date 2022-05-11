package com.luo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luo.entity.BookFile;
import com.luo.mapper.BookFileMapper;
import com.luo.service.BookFileService;
import org.springframework.stereotype.Service;

@Service
public class BookFileServiceImpl extends ServiceImpl<BookFileMapper, BookFile> implements BookFileService {
}
