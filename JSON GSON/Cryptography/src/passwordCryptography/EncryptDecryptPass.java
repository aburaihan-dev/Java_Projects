package passwordCryptography;

/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class EncryptDecryptPass {

    public static void main(String[] args) {
        try {
            String password = "Summer2016";
            System.out.println("plain pass="+password);
            String encryptedPassword = AESCrypt.encrypt(password);
            System.out.println("encrypted pass="+encryptedPassword);
            String decryptedPassword = AESCrypt.decrypt(encryptedPassword);
            System.out.println("decrypted pass="+decryptedPassword);
        } catch(Exception e) { System.out.println("bug"+e.getMessage()); }
    }

}
