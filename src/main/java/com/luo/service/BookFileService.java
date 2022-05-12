package com.luo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.entity.BookFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BookFileService extends IService<BookFile> {

    String filePath = "D:\\Work\\GIT\\GitFile\\github\\myspringboot\\upload";

    BookFile upload(Integer bookId, MultipartFile multipartFile) throws IOException;

    BookFile download(Integer id, HttpServletResponse response);
}
