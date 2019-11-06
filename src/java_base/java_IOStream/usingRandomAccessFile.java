package java_base.java_IOStream;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author: yk
 * @Date: 2019/11/6 11:51
 */
public class usingRandomAccessFile {
    /**
     * RandomAccessFile 可实现读写随机访问文件
     * 注： 不支持装饰，无法与 InputStream 和 OutPutStream 组合使用
     */
    static String file = "randomAccess.dat";

    static void display() throws IOException {
        // "r" -> 只读
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        for (int i = 0; i < 7; i++) {
            System.out.println("Value" + i + ": " + raf.readDouble());
        }
        System.out.println(raf.readUTF());
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        // create the file     "rw" -> 读写
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        // write the file
        for (int i = 0; i < 7; i++) {
            rf.writeDouble(i * 1.414);
        }
        rf.writeUTF("The end of the file!");
        rf.close();
        // open and print the elements of the file
        display();
        rf = new RandomAccessFile(file, "rw");
        // move to the given position (double: 8 byte  -> 第五个双精度: 5*8)
        rf.seek(5 * 8);
        // modify the value of the given position
        rf.writeDouble(47.0001);
        rf.close();
        display();
    }
}
