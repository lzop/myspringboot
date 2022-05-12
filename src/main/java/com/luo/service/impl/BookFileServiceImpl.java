package com.luo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luo.entity.BookFile;
import com.luo.mapper.BookFileMapper;
import com.luo.service.BookFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

@Slf4j
@Service
public class BookFileServiceImpl extends ServiceImpl<BookFileMapper, BookFile> implements BookFileService {

    @Resource
    private BookFileMapper bookFileMapper;

    @Override
    public BookFile upload(Integer bookId, MultipartFile multipartFile) throws IOException {


        //文件上传
        //1、定义文件上传位置
        File file = new File(filePath + "//" + multipartFile.getOriginalFilename());
        //2、文件上传
        multipartFile.transferTo(file);

        //存储记录
        BookFile bookFile = new BookFile();
        bookFile.setBookId(bookId);
        bookFile.setPath(file.getPath());
        int insert = bookFileMapper.insert(bookFile);
        if (insert < 0) {
            log.info("文件删除:{}", file.delete());
            bookFile = null;
        }
        return bookFile;
    }

    @Override
    public BookFile download(Integer id, HttpServletResponse response) {

        //查询文件路径
        BookFile bookFile = bookFileMapper.selectById(id);

        if (Objects.nonNull(bookFile)) {
            //文件下载
            File file = new File(bookFile.getPath());

            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            response.setContentType("application/force-download");
            response.setCharacterEncoding("utf-8");

            InputStream is = null;
            OutputStream os = null;
            byte[] bytes = new byte[1024];
            int temp;

            try {
                is = new FileInputStream(file);
                os = response.getOutputStream();

                while ((temp = is.read(bytes)) != -1) {
                    os.write(bytes, 0, temp);
                    os.flush();
                }
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
        }

        return bookFile;
    }
}
