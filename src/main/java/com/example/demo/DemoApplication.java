package com.example.demo;

import com.example.demo.tool.CommonUtil;
import com.example.demo.tool.ExcelTemplateGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		InputStream is = null;
//		try {
//			is = CommonUtil.createFileInputStream("C:\\Users\\85241\\Desktop\\test.xls");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		String template = "hello #{0} , ql #{2}";
//		ExcelTemplateGenerator generator = new ExcelTemplateGenerator(template, is);
//		String content = generator.excute(0, 0, 0);
//		System.err.println(content);
	}

}
