package com.luo.controller;

import com.luo.entity.BookFile;
import com.luo.exception.ExceptionEnum;
import com.luo.exception.ResultResponse;
import com.luo.service.BookFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/bookFile")
public class BookFileController {

    @Resource
    private BookFileService bookFileService;

    @PostMapping(value = "/upload")
    public ResultResponse upload(@RequestParam(value = "bookId") Integer bookId,
                                 @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {

        if (bookId > 0) {
            BookFile bookFile = bookFileService.upload(bookId, multipartFile);
            return Objects.isNull(bookFile) ? ResultResponse.error("存储失败") : ResultResponse.success(bookFile);
        } else {
            return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        }

    }

    @GetMapping(value = "/download")
    public ResultResponse download(@RequestParam(value = "id") Integer id,
                                   HttpServletResponse response) {
        if (id > 0) {
            BookFile bookFile = bookFileService.download(id, response);
            return Objects.isNull(bookFile) ? ResultResponse.error("下载失败") : ResultResponse.success(bookFile);
        } else {
            return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResultResponse delete(@RequestParam(value = "id") Integer id) {
        if (id > 0) {
            BookFile bookFile = bookFileService.getById(id);
            boolean remove = bookFileService.removeById(id);
            return remove ? ResultResponse.error("删除失败") : ResultResponse.success(bookFile);
        } else {
            return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        }
    }


}
