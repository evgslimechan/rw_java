package com.slimechan.raceway_system.manages;

import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SessionManager {

    private Map<String, String> tokens = new HashMap<>();

    public void setToken(String ip, String token){
        if(!tokens.containsKey(ip))
            tokens.replace(ip, token);
        else tokens.put(ip, token);
    }

    public boolean containsUser(String ip){
        return tokens.containsKey(ip);
    }

    public String generateToken(int n){
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }
        return r.toString();
    }

}
