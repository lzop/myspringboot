package com.luo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin
public class FileController {


    @PostMapping(value = "/upload")
    public void upload(@RequestParam(value = "file", required = true) MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getSize());
        try {
            File file = new File(System.getProperty("user.dir") + "/upload/" + multipartFile.getOriginalFilename());
            file.mkdirs();
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping(value = "/download")
    public void download(@RequestParam(value = "id", required = true) String id, HttpServletRequest request, HttpServletResponse response) {
        //通过id去找文件存储的全局路径
        System.out.println(id);

        String filePath = "D:\\Work\\GIT\\GitFile\\github\\myspringboot\\upload\\point.csv";

        //准备输入输出流

        InputStream is = null;

        OutputStream os = null;

        byte[] bytes = new byte[1024];

        //设置请求头
        response.setHeader("Content-Disposition", "attachment;filename=point.csv");
        //设置响应内容类型
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");

        try {
            is = new FileInputStream(filePath);
            os = response.getOutputStream();

            int len = 0;

            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
                os.flush();
            }

            System.out.println("文件下载结束！！！");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

}
