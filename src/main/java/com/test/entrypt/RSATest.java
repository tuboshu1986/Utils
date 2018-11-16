package com.test.entrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSATest
{
    public static String PUBLIC_KEY = "PUBLIC_KEY";
    public static String PRIVATE_KEY = "PRIVATE_KEY";

    public static Map<String, Object> initKey() throws Exception
    {
        Map<String, Object> rst = null;

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        KeyPair keypair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keypair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keypair.getPrivate();

        rst = new HashMap<String, Object>();
        rst.put(RSATest.PUBLIC_KEY, publicKey);
        rst.put(RSATest.PRIVATE_KEY, privateKey);

        return rst;
    }

    public static byte[] entrypt(byte[] data, RSAPublicKey publicKey) throws Exception
    {
        byte[] rst = null;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        rst = cipher.doFinal(data);

        return rst;
    }

    public static byte[] dectrypt(byte[] data, RSAPrivateKey privateKey) throws Exception
    {
        byte[] rst = null;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        rst = cipher.doFinal(data);

        return rst;
    }
    
    public static void main(String[] args) throws Exception
    {
        String msg = "Hello World!!!";
        byte[] bs = msg.getBytes();
        
        Map<String, Object> key = RSATest.initKey();
        
        bs = RSATest.entrypt(bs, (RSAPublicKey)key.get(RSATest.PUBLIC_KEY));
        System.out.println(new String(bs));

        bs = RSATest.dectrypt(bs, (RSAPrivateKey)key.get(RSATest.PRIVATE_KEY));
        System.out.println(new String(bs));
    }
}
