package java_base.java_IOStream;

import java.io.*;

/**
 * @Author: yk
 * @Date: 2019/11/5 17:15
 */
public class Output_File {
    static String file = "basicOutput.out";

    public static void basicOutput(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(
                new StringReader(
                        Input_File.read(fileName)
                )
        );
        // 用PrintWriter来提供格式化机制 -- 按照该方法创建的数据文件可作为普通文本文件读取
        PrintWriter pw = new PrintWriter(
                // 用BufferedWriter 包装以作缓冲输出 -- 可显著增加I/O操作性能
                new BufferedWriter(new FileWriter(file))
        );
        /**
         * here is the shortcut: 仍旧在进行缓存，只是不必自己实现 （Java SE5添加）
         *      PrintWriter pw = new PrintWriter(fileName);
         */
        // 行号
        int lineCount = 1;
        String s;
        // write the file -- the number of line will be increased in writing each line
        // 读完数据流后，readLine() 会返回null
        while ((s = br.readLine()) != null) {
            pw.println(lineCount++ + ": " + s);
        }
        // 清空缓冲区数据
        pw.close();
        // show the stored file
        System.out.println(Input_File.read(file));
    }

    public static void main(String[] args) throws IOException {
        // 基本文件输出实现
        basicOutput("D:\\IDEA\\project\\Java_learning\\src\\java_base\\make_code_flexible\\select_apple\\AppleFormatter.java");
    }
}
