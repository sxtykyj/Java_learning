package java_base.java_IOStream;

import java.io.*;

/**
 * @Author: yk
 * @Date: 2019/11/5 17:51
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        // 读和写数据平台不同，DataOutputStream也可以准确读取数据
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Data.txt")
                )
        );
        out.writeDouble(3.1415926);
        out.writeUTF("That is the PI! ");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();

        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("Data.txt")
                )
        );
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
