package com.luo.controller;

import com.luo.entity.Book;
import com.luo.entity.BookFile;
import com.luo.service.BookFileService;
import com.luo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BookController {

    private static final String filePath = "D:\\Work\\GIT\\GitFile\\github\\myspringboot\\upload";

    @Resource
    private BookService bookService;

    @Resource
    private BookFileService bookFileService;

    @PostMapping(value = "/add")
    public String addBook(@RequestBody Book book) {
        boolean save = bookService.save(book);
        return save ? "成功" : "失败";
    }

    @PostMapping(value = "/add/file")
    public String addBookAndFile(@RequestParam String bookName,
                                 @RequestParam String bookSummary,
                                 String author,
                                 String remark,
                                 @RequestParam(value = "file") MultipartFile multipartFile) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setBookSummary(bookSummary);
        book.setAuthor(author);
        book.setRemark(remark);
        boolean save = bookService.save(book);
        if (save) {
            log.info("insert success , book is :{}", book);
            String fPath = filePath + "\\" + multipartFile.getOriginalFilename();
            try {
                File file = new File(fPath);
                multipartFile.transferTo(file);
                if (file.exists()) {
                    BookFile bookFile = new BookFile();
                    bookFile.setBookId(book.getId());
                    bookFile.setPath(fPath);
                    boolean flag = bookFileService.save(bookFile);
                    return flag ? "成功" : "失败";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "失败！！！";
    }


    @GetMapping(value = "/get/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getBook(Integer.parseInt(id));
    }


    @GetMapping(value = "/file/{id}")
    public String getBookFile(@PathVariable(value = "id") String id,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        BookFile bookFile = bookFileService.getById(id);

        if (bookFile == null) {
            return "id不存在！！！";
        }

        String path = bookFile.getPath();

        //设置头文件格式
        response.setHeader("Content-Disposition", "attachment;filename=" + path);
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");

        //准备文件传输流
        InputStream is = null;
        OutputStream os = null;

        byte[] bytes = new byte[1024];

        int temp = -1;

        try {
            is = new FileInputStream(path);
            os = response.getOutputStream();

            while ((temp = is.read(bytes)) != -1) {
                os.write(bytes, 0, temp);
                os.flush();
            }

            log.info("文件传输结束!!!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (os != null) {
                    os.close();
                }

                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "文件传输完成！！！";
    }

}
