// Ava Nunes
// 01/16/24
// CSE 123
// TA: Ben Wang
// This class extends the Substitution abstract class, it replaces all characters from an input
// string with the equivalent characters from a given shifter string when encrypting

public class CaesarKey extends Substitution {
    private String key;

    // behavior: constructs a new caesarkey cipher with the given key
    // parameters: key - key to be applied to the new caesarkey cipher
    // returns: n/a, constructor
    public CaesarKey(String key) {
        super();
        if(key == "" || duplicateCheck(key) || rangeCheck(key)) {
            throw new IllegalArgumentException();
        }

        this.key = key;
    }

    // behavior: applies this caesarkeys's encryption scheme to the given string
    // parameters: input - string to be encrypted
    // returns: resulting encrypted string
    // exceptions: throws new IllegalStateException when the inputted string is empty or null
    public String encrypt(String input) {
        setShifter(shiftRange(key)); 
        return super.encrypt(input);
    }

    // behavior: applies this caesarkey's inverse encryption scheme to the given string
    // parameters: input - string to be deencrypted
    // returns: resulting decrypted string
    // exceptions: throws new IllegalStateException when the inputted string is empty or null
    public String decrypt(String input) {  
        setShifter(shiftRange(key)); 
        return super.decrypt(input);
    }

    // behavior: shifts the encodable range with the given key
    // parameters: key - letters to shift the encodable range with
    // returns: n/a, void method
    private String shiftRange(String key) {
        String newShifter = "";
        String shifterAux = "";

        for(int i = MIN_CHAR; i <= MAX_CHAR; i++) {
            shifterAux += (char)(i);
        }

        for(int i = 0; i < key.length(); i++) {
            char keyChar = key.charAt(i);
            newShifter += keyChar; // adds keychar 
            char letter = shifterAux.charAt(i);
            shifterAux = shifterAux.replace(keyChar, letter);
        }

        shifterAux = shifterAux.substring(key.length());
        for(int i = 0; i < shifterAux.length(); i++) {
            char letter = shifterAux.charAt(i);
            newShifter += letter;
        }

        return newShifter;
    }

}