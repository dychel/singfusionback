package com.singfusion.singfusion.service;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;

@Service
public class EcobankHashServiceImpl implements EcobankHashSercive{

//    @Value("${LAB_KEY}")
    private String LAB_KEY;
    @Override
    public String Hashing(String payload) {
        LAB_KEY="0C/5F7QHdMv40uVGaTbt5nXdJOxi105k2LN9goPRqTUrwZrdYOYbvC0sJz7G0iT9";
        String result="";
        try {
            String data = payload + LAB_KEY;
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // ** NOTE all bytes that are retrieved from the data string must be done so using UTF-8 Character Set.
            byte[] hashBytes = (data).getBytes("UTF-8");
            //Create the hash bytes from the data
            byte[] messageDigest = digest.digest(hashBytes);
            //Create a HEX string from the hashed data
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                sb.append(h);
            }
            result = sb.toString();
        } catch (Exception ex) {
            //Log your errors;
        }
        //sample result :2611e1ad3a8077020c55a227408329f0b2bb4f00c0e409a93abd69dba133c5d5a7d7575fef87cb13a9d5319c2f78199d8e674ce6f7d63acea95ae5a214ad9f5a
        return result;
    }

}
