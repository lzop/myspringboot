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
    public List<Book> getBooks(Book book) {

        QueryWrapper<Book> wrapper = new QueryWrapper<>();

        if (book != null) {
            if (book.getId() != null) {
                wrapper.lambda().eq(Book::getId, book.getId());
            }
            if (book.getBookName() != null) {
                wrapper.lambda().eq(Book::getBookName, book.getBookName());
            }
            if (book.getBookSummary() != null) {
                wrapper.lambda().eq(Book::getBookSummary, book.getBookSummary());
            }
            if (book.getAuthor() != null) {
                wrapper.lambda().eq(Book::getAuthor, book.getAuthor());
            }
            if (book.getRemark() != null) {
                wrapper.lambda().eq(Book::getRemark, book.getRemark());
            }
        }

        List<Book> books = bookMapper.selectList(wrapper);

        for (Book b : books) {
            QueryWrapper<BookFile> wrapper1 = new QueryWrapper<>();
            wrapper1.lambda().eq(BookFile::getBookId, b.getId());

            b.setBookFileList(bookFileMapper.selectList(wrapper1));
        }

        return books;
    }
}
