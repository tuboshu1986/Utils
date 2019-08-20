package com.hb.utils.base;

import org.bouncycastle.util.encoders.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化和反序列化
 */
public class SeriaizeUtil {

    /**
     * 反序列化
     * @param serializedStr 包含对象序列化信息的字符串
     * @param clazz 目标对象
     * @param <T>
     * @return
     */
    public static <T> T unserialized(String serializedStr, Class<T> clazz){
        ObjectInputStream in = null;
        ByteArrayInputStream buffer = null;

        try {
            buffer = new ByteArrayInputStream(Base64.decode(serializedStr));
            in = new ObjectInputStream(buffer);
            return  (T)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(in);
            close(buffer);
        }

        return null;
    }

    /**
     * 序列化对象
     * @param obj
     * @return
     */
    public static String serialized(Object obj){
        ObjectOutputStream out = null;
        ByteArrayOutputStream buffer = null;

        try {
            buffer = new ByteArrayOutputStream();
            out = new ObjectOutputStream(buffer);
            out.writeObject(obj);
            out.flush();

            return Base64.toBase64String(buffer.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(out);
            close(buffer);
        }

        return null;
    }

    private static void close(Closeable c){
        if(c != null){
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
