// Ava Nunes
// 01/17/24
// CSE 123
// TA: Ben Wang
// This class ...


public class Concealment extends Cipher {
    private int filler;
    
    public Concealment(int filler) {
        if(filler <= 0) {
            throw new IlleagalArgumentException("Filler can't be less than or equal to zero!");
        }

        this.filler = filler;
    }
    
    public String encrypt(String input) {
        Random random = new Random();
        String encryption = "";
        for(int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            for(int j = 0; j < filler; j++) {
                char addition = (char)(random.nextInt(MIN_CHAR, MAX_CHAR));
                encryption += addition;
            }

            encryption += letter;
        }
        return encryption; 
    }
    
    public String decrypt(String input) {
        String decryption = "";
        for(int i = filler; i < input.length(); i += filler) {
            char letter = input.charAt(i);
            decryption += letter;
        }

        return decryption;
    }
}
