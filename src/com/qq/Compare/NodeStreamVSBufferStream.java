package com.qq.Compare;

import java.io.*;
//节点流和缓冲流的对比
public class NodeStreamVSBufferStream {
    public static void main(String[] args) throws IOException {
        //创建源和目标
        File srcFile = new File("F://Music//MV//光年之外.mp4");
        File destFile = new File("F://Music//CopyMV//光年之外(1).mp4");
        Text1(srcFile,destFile);//使用节点流从磁盘一个一个字节读取,时间较长，并没有运行出来
        Text2(srcFile,destFile);//使用缓冲流从磁盘一个一个字节读取       耗时：4433
        Text3(srcFile,destFile);//使用节点流从磁盘没次读写1024字节      耗时：1079
        Text4(srcFile,destFile);//使用缓冲流从磁盘没次读写1024字节     耗时：283
    }

    //使用节点流从磁盘一个一个字节读取
    private static void Text1(File srcFile, File destFile) throws IOException {
        long begin = System.currentTimeMillis();    //读取当前时间
        //创建输入/输出流对象
        // 把异常抛了出去，不然代码不清晰
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);
        int len;        //读取的字节数,为-1表示读取完了
        while((len = in.read()) != -1){
            out.write(len);
        }
        //关闭流
        in.close();
        out.close();
        //打印消耗的时间
        System.out.println("耗时：" + (System.currentTimeMillis() - begin));
    }
    //使用缓冲流从磁盘一个一个字节读取      耗时：4433
    private static void Text2(File srcFile, File destFile) throws IOException {
        long begin = System.currentTimeMillis();    //读取当前时间

        // 把异常抛了出去，不然代码不清晰
        // 创建输入/输出流对象,并用缓冲流包装，用它的父类接收
        InputStream in = new BufferedInputStream(new FileInputStream(srcFile));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
        int len;        //读取的字节数,为-1表示读取完了
        while((len = in.read()) != -1){
            out.write(len);
        }
        //关闭流
        in.close();
        out.close();
        //打印消耗的时间
        System.out.println("耗时：" + (System.currentTimeMillis() - begin));
    }
    //使用节点流从磁盘没次读写1024字节        耗时：1079
    private static void Text3(File srcFile, File destFile) throws IOException {
        long begin = System.currentTimeMillis();    //读取当前时间
        //创建输入/输出流对象
        // 把异常抛了出去，不然代码不清晰
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);
        int len;        //读取的字节数,为-1表示读取完了
        byte[] buffer = new byte[1024];//创建容量为1024的缓冲区
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        //关闭流
        in.close();
        out.close();
        //打印消耗的时间
        System.out.println("耗时：" + (System.currentTimeMillis() - begin));
    }
    //使用缓冲流从磁盘每次读写1024个字节      耗时：283
    private static void Text4(File srcFile, File destFile) throws IOException {
        long begin = System.currentTimeMillis();    //读取当前时间

        // 把异常抛了出去，不然代码不清晰
        // 创建输入/输出流对象,并用缓冲流包装，用它的父类接收
        InputStream in = new BufferedInputStream(new FileInputStream(srcFile));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
        int len;        //读取的字节数,为-1表示读取完了
        byte[] buffer = new byte[1024];//创建容量为1024的缓冲区
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        //关闭流
        in.close();
        out.close();
        //打印消耗的时间
        System.out.println("耗时：" + (System.currentTimeMillis() - begin));
    }
}
