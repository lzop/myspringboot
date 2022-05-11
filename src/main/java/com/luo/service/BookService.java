package com.luo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.entity.Book;

public interface BookService extends IService<Book> {

    Book getBook(Integer id);

}
