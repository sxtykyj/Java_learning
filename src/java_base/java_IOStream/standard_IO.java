package java_base.java_IOStream;

import java.io.*;

/**
 * @Author: yk
 * @Date: 2019/11/6 12:30
 */
public class standard_IO {
    /**
     * 从标准输入中读取, 直接回显输入的每一行并将所有字符转为大写
     *
     * @throws IOException
     */
    public static void readFromStandardInput() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        while ((s = br.readLine()) != null && s.length() != 0) {
            System.out.println(s.toUpperCase());
        }
    }

    /**
     * 将 System.out 转换成 PrintStream
     * System.out : 一个PrintStream
     * PrintStream: 一个OutPutStream
     *
     * @throws IOException
     */
    public static void changeSystemOut() throws IOException {
        // true 参数意味着开启自动清空功能，否则可能看不到输出
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world!");
    }

    /**
     * 标准 I/O 重定向 -- 操纵字节流
     *
     * @throws IOException
     */
    public static void redirecting() throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("Redirecting.java")
        );
        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("test.out")
                )
        );
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }

    public static void main(String[] args) throws IOException {
        changeSystemOut();
        redirecting();
        readFromStandardInput();

    }

}
