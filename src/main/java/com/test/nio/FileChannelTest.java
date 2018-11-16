package com.test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest
{
    public static void main(String[] args) throws Exception
    {
        long start,end;
        start = System.currentTimeMillis();
        FileChannelTest.nioCopy();
        System.out.println((end = System.currentTimeMillis()) - start);
        start = end;
        FileChannelTest.ioCopy();
        System.out.println((end = System.currentTimeMillis()) - start);
    }
    
    public static void nioCopy() throws Exception{
        FileInputStream in = new FileInputStream("D:/3dsmax9chinese32bit.zip");
        FileOutputStream out = new FileOutputStream("D:/tmp/3dsmax9chinese32bit.zip");
        
        FileChannel inFileChannel = in.getChannel();
        FileChannel outFileChannel = out.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        @SuppressWarnings("unused")
        int len;
        while((len = inFileChannel.read(buffer))>0){
            buffer.flip();
            outFileChannel.write(buffer);
            buffer.clear();
        }
        
        inFileChannel.close();
        outFileChannel.close();
        in.close();
        out.close();
    }
    

    public static void ioCopy() throws Exception{
        FileInputStream in = new FileInputStream("D:/3dsmax9chinese32bit.zip");
        FileOutputStream out = new FileOutputStream("D:/tmp/3dsmax9chinese32bit1.zip");
        
        byte[] buffer = new byte[1024];
        
        int len;
        while((len = in.read(buffer))>0){
            out.write(buffer, 0, len);
            out.flush();
        }
        
        in.close();
        out.close();
    }
    
}
