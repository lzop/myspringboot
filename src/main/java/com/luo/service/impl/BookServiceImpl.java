package com.luo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luo.entity.Book;
import com.luo.entity.BookFile;
import com.luo.mapper.BookFileMapper;
import com.luo.mapper.BookMapper;
import com.luo.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookFileMapper bookFileMapper;

    @Override
    public Book getBook(Integer id) {
        Book book = bookMapper.selectById(id);

        if (book != null) {
            QueryWrapper<BookFile> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(BookFile::getBookId, book.getId());
            List<BookFile> bookFiles = bookFileMapper.selectList(queryWrapper);

            book.setBookFileList(bookFiles);
        }

        return book;
    }
}
