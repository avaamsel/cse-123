// Ava Nunes
// 01/13/24
// CSE 123
// TA: Ben Wang
// This class extends the Cipher abstract class, it replaces all characters from an input
// string with the equivalent characters after shifting the encodable range by the key when
// encrypting

public class CaesarShift extends Cipher{
    private int shift;

    // behavior: constructs a new caesarshift cipher with the given shift value
    // parameters: shift - number to shift the shifter by
    // returns: n/a, constructor
    public CaesarShift(int shift) {
        if(shift <= 0) {
            throw new IllegalArgumentException("You can't shift by a negative value!");
        }

        this.shift = shift;
    }

    // behavior: applies this caesarshift's encryption scheme to the given string
    // parameters: input - string to be encrypted
    // returns: resulting encrypted string
    public String encrypt(String input) {
        String encryption = "";
        for(int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            int num = (int)(letter) + shift;
            if(num > MAX_CHAR) {
                num = MIN_CHAR + (shift - 1);
            }

            encryption += (char)(num);

        }

        return encryption;
    }

    // behavior: applies this caesarshift's inverse encryption scheme to the given string
    // parameters: input - string to decrypt
    // returns: resulting decrypted string
    public String decrypt(String input) {
        String decryption = "";
        for(int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            int num = (int)(letter);
            decryption += (char)(num - shift);
        }

        return decryption;
    }
}
