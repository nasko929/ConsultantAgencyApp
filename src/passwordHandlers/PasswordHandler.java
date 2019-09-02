package passwordHandlers;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

public class PasswordHandler {
    private static final char[] allowedSymbols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final int iterations = 15 * 1000;
    private static final int key_length = 512;

    public static String generateSalt() {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            salt.append(allowedSymbols[new Random().nextInt(62)]);
        }
        return salt.toString();
    }

    public static String hashPassword(String password, String salt){
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, key_length);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
            byte[] securePassword = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(securePassword);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        } finally {
            pbeKeySpec.clearPassword();
        }

    }
}
