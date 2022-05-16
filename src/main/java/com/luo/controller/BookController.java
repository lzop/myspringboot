package com.luo.controller;

import com.luo.entity.Book;
import com.luo.exception.ExceptionEnum;
import com.luo.exception.ResultResponse;
import com.luo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Resource
    private BookService bookService;


    @GetMapping(value = "/get")
    public ResultResponse getBook(@RequestParam(value = "id") Integer id) {
        if (id > 0) {
            Book book = bookService.getById(id);
            return ResultResponse.success(book);
        } else {
            return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        }
    }

    @GetMapping(value = "/gets")
    public ResultResponse getBooks(Book book) {
        List<Book> books = bookService.getBooks(book);
        return ResultResponse.success(books);
    }

    @PostMapping(value = "/add")
    public ResultResponse addBook(@RequestBody Book book) {
        boolean save = bookService.save(book);
        return save ? ResultResponse.success(book) : ResultResponse.error("添加失败");
    }

    @PutMapping(value = "/update")
    public ResultResponse updateBook(@RequestBody Book book) {
        boolean flag = bookService.updateById(book);
        return flag ? ResultResponse.success(book) : ResultResponse.error("修改失败");
    }

    @DeleteMapping(value = "/delete")
    public ResultResponse deleteBook(@RequestParam(value = "id") Integer id) {
        if (id > 0) {
            boolean flag = bookService.removeById(id);
            return flag ? ResultResponse.success(id) : ResultResponse.error("删除失败");
        } else {
            return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        }
    }

}
