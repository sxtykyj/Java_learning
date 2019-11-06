package java_base.java_IOStream;

import java.io.*;

/**
 * @Author: yk
 * @Date: 2019/11/4 15:12
 */
public class Input_File {

    /**
     * using BufferedReader (缓冲输入文件) -- 字符输入
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String read(String fileName) throws IOException {
        // read input by lines
        BufferedReader br = new BufferedReader(
                new FileReader(fileName)
        );
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            // readline() 会删掉换行符，因此需要添加 "\n"
            sb.append(s + "\n");
        }
        // close() 关闭文件
        br.close();
        return sb.toString();
    }

    /**
     * using BufferedInputFile ( 内存读入文件)   -- 字符输入
     *
     * @param fileName
     * @throws IOException
     */
    public static String readFromMemory(String fileName) throws IOException {
        StringReader sr = new StringReader(
                read(fileName)
        );
        int c;
        StringBuilder sb = new StringBuilder();
        while ((c = sr.read()) != -1) {
            // read() 是以int形式返回，则需要类型转换为char
            sb.append((char) c);
        }
        return sb.toString();
    }

    /**
     * using DataInputStream  -- 格式化的内存输入（面向字节）
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFromStream(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            DataInputStream dis = new DataInputStream(
                    new ByteArrayInputStream(
                            read(fileName).getBytes()
                    )
            );
            /**
             * 用 readByte() 一次一个字节地读取字符，那么任何字节的值都是合法的，因此返回值不能用来检测输入是否结束
             * 因此，该处用available() 查看还有多少可读取的字符
             * 注： available() 的工作方式会随着所读取的媒介类型的不同而不同
             *      因此，对于文件是意味着整个文件，但是对于不同类型的流则不是；需谨慎使用
             */
            while (dis.available() != 0) {
                sb.append((char) dis.readByte());
            }
            // 也可将while的循环条件设为true，用异常来检测输入是否结束，但是该方法不建议使用
        } catch (EOFException e) {
            System.err.println("End of stream.");
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        // using BufferedReader (缓冲输入文件) -- 字符输入
        System.out.println(read("D:\\IDEA\\project\\Java_learning\\src\\java_base\\make_code_flexible\\select_apple\\Apple.java"));
        // using BufferedInputFile ( 内存读入文件)   -- 字符输入
        System.out.println(readFromMemory("D:\\IDEA\\project\\Java_learning\\src\\java_base\\make_code_flexible\\select_apple\\Apple.java"));
        // using DataInputStream  -- 格式化的内存输入（面向字节）
        System.out.println(readFromStream("D:\\IDEA\\project\\Java_learning\\src\\java_base\\make_code_flexible\\select_apple\\Apple.java"));

    }
}
