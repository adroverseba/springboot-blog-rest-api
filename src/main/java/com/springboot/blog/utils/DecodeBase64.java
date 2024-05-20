package com.springboot.blog.utils;

import io.jsonwebtoken.io.Decoders;

public class DecodeBase64 {
    public static void main(String[] args) {
        String jwtSecret = "miSecretoEnBase64";
        byte[] secretBytes = Decoders.BASE64.decode(jwtSecret);

        for (byte b: secretBytes
             ) {
            System.out.println(b);
        }
    }
}
