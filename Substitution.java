// Ava Nunes
// 01/13/24
// CSE 123
// TA: Ben Wang
// This class extends the Cipher abstract class, it replaces all characters from an input
// string with the equivalent characters from a given shifter string when encrypting

public class Substitution extends Cipher {
    private String shifter;

    // behavior: constructs a new substitution cipher with an empty shifter
    // parameters: n/a
    // returns: n/a, constructor
    public Substitution() {
        this.shifter = "";
    }

    // behavior: constructs a new substitution cipher with the given shifter
    // parameters: shifter - shifter to be applied to the new substitution cipher
    // returns: n/a, constructor
    // exceptions: throws new IllegalArgumentException is the given shifter has any duplicate chars, falls outside
    //             the encodable range, or doesn't have the same length as TOTAL_CHARS
    public Substitution(String shifter) {
        if(shifter.length() != TOTAL_CHARS || duplicateCheck(shifter) || rangeCheck(shifter)) {
            throw new IllegalArgumentException();
        }

        this.shifter = shifter;
    }

    // behavior: sets a new shifter for the current substitution cipher
    // parameters: shifter - shifter to be set to the substitution cipher
    // returns: n/a, void  method
    // exceptions: throws new IllegalArgumentException is the given shifter has any duplicate chars, falls outside
    //             the encodable range, or doesn't have the same length as TOTAL_CHARS
    public void setShifter(String shifter) {
        if(shifter.length() != TOTAL_CHARS || duplicateCheck(shifter) || rangeCheck(shifter)) {
            throw new IllegalArgumentException();
        }

        this.shifter = shifter;
    }

    // behavior: applies this subtitutions's encryption scheme to the given string
    // parameters: input - string to be encrypted
    // returns: resulting encrypted string
    // exceptions: throws new IllegalStateException when the inputted string is empty or null
    public String encrypt(String input) {
        if(shifter == "" || shifter == null) {
            throw new IllegalStateException();
        }

        String encryption = "";
        for(int i = 0; i < input.length(); i++) {
            int letterNum = (int)(input.charAt(i));
            int index = (letterNum - MIN_CHAR);
            encryption += shifter.charAt(index);
        }
        
        return encryption;
    }

    // behavior: applies this subtitutions's inverse encryption scheme to the given string
    // parameters: input - string to be deencrypted
    // returns: resulting decrypted string
    // exceptions: throws new IllegalStateException when the inputted string is empty or null
    public String decrypt(String input) {
        if(shifter == "" || shifter == null) {
            throw new IllegalStateException();
        } 
        
        String decryption = "";
        for(int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            for(int j = 0; j < shifter.length(); j++) {
                char shiftChar = shifter.charAt(j);
                if(shiftChar == letter) {
                    int index = MIN_CHAR + j;
                    decryption += (char)(index);
                }
            }
        }

        return decryption;
    }

    // behavior: checks to see if the given shifter has any duplicate characters
    // parameters: shifter - string to check
    // returns: true if the given string has any duplicate characters, false if otherwise
    public boolean duplicateCheck(String shifter) {
        for(int i = 0; i < shifter.length(); i++) {
            char check = shifter.charAt(i);
            for(int j = i + 1; j < shifter.length(); j++) {
                char compare = shifter.charAt(j);
                if(check == compare) {
                    return true;
                }
            }
        }

        return false;
    }

    // behavior: checks to see if the given shifter falls within the encodable range
    // parameters: shifter - string to check
    // returns: true if the string falls outside the encodable range, false if otherwise
    public boolean rangeCheck(String shifter) {
        for(int i = 0; i < shifter.length(); i++) {
            char letter = shifter.charAt(i);
            int rangeNum = (int)(letter);
            if(rangeNum < MIN_CHAR || rangeNum > MAX_CHAR) {
                return true;
            }
        }

        return false;
    }

}