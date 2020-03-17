package com.example.demo.controller;

import com.example.demo.tool.CommonUtil;
import com.example.demo.tool.ExcelTemplateGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/excelTemplate")
public class ExcelTemplateController {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("excel") MultipartFile file,
                       @RequestParam("template") String template,
                       @RequestParam("startRow") int startRow,
                       @RequestParam("endRow") int endRow,
                       @RequestParam("sheetNum") int sheetNum,
                       HttpServletResponse response) throws IOException {

        InputStream is = file.getInputStream();
        ExcelTemplateGenerator generator = new ExcelTemplateGenerator(template, is);
        String content = generator.excute(sheetNum, startRow, endRow);
        return content;
//        CommonUtil.download(response, content, "temp.txt");
    }

}
