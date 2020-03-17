package com.example.demo.tool;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CommonUtil {

    public static boolean isStrEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || str.equals("")) {
                return true;
            }
        }
        return false;
    }

    public static void download(HttpServletResponse response, String content, String fileName) {
        response.setContentType("application/x-download");
        /* 设置文件头：最后一个参数是设置下载文件名(假如我们叫a.ini)   */
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            /* 用流将数据写给前端 */
            OutputStream os = response.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            os.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static InputStream createFileInputStream(String filePath) throws FileNotFoundException {
        if (filePath == null || "".equals(filePath)) {
            throw new RuntimeException("File path can't be null.");
        }
        return new FileInputStream(new File(filePath));
    }
}
