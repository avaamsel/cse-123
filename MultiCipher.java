// Ava Nunes
// 01/13/24
// CSE 123
// TA: Ben Wang
// This class ...

import java.util.*;

public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    // behaviors: constructs a new multicipher cipher with the given list of ciphers
    // parameters: ciphers - list containing the ciphers needed to be implemented
    // returns: n/a, constructor
    public MultiCipher(List<Cipher> ciphers) {
        if(ciphers == null) {
            throw new IllegalArgumentException();
        }
        
        this.ciphers = new ArrayList<>();
        this.ciphers = ciphers;
    }

    // behavior:
    // parameters:
    // returns:
    public String encrypt(String input) {
        String encryption = "";
        List<String> inputs = new ArrayList<>();
        for(int i = 0; i < ciphers.size(); i++) {
            Cipher cipher = ciphers.get(i);
            String result = cipher.encrypt(input);
            inputs.add(result);
            for(int j = 1; j < ciphers.size(); j++) {
                Cipher cipher = ciphers.get(j);
                String result = cipher.encrypt(result + i);
                inputs.add(result);
            }
        }

        return encryption;
    }

    // behavior:
    // parameters:
    // returns:
    public String decrypt(String input) {
        String decryption = "";
        for(int i = ciphers.size(); i > 0; i--) {

        }
        
        return decryption;
    }

}
