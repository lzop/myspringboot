package com.luo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.entity.Book;

import java.util.List;

public interface BookService extends IService<Book> {

    List<Book> getBooks(Book book);

}
