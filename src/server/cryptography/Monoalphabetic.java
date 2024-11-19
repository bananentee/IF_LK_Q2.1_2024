package server.cryptography;

public class Monoalphabetic {

    public static void main(String[] args) {
        new Monoalphabetic().createCodeAlphabet("CODEE");
        System.out.println(new Monoalphabetic().encode("hallo was machen sachen","CODEE"));
        System.out.println(new Monoalphabetic().decode(new Monoalphabetic().encode("hallo was machen sachen","CODEE"), "CODEE"));
    }

    private final char[] alphabet = new char[26];

    Monoalphabetic() {
        alphabet[0] = 'A';
        alphabet[1] = 'B';
        alphabet[2] = 'C';
        alphabet[3] = 'D';
        alphabet[4] = 'E';
        alphabet[5] = 'F';
        alphabet[6] = 'G';
        alphabet[7] = 'H';
        alphabet[8] = 'I';
        alphabet[9] = 'J';
        alphabet[10] = 'K';
        alphabet[11] = 'L';
        alphabet[12] = 'M';
        alphabet[13] = 'N';
        alphabet[14] = 'O';
        alphabet[15] = 'P';
        alphabet[16] = 'Q';
        alphabet[17] = 'R';
        alphabet[18] = 'S';
        alphabet[19] = 'T';
        alphabet[20] = 'U';
        alphabet[21] = 'V';
        alphabet[22] = 'W';
        alphabet[23] = 'X';
        alphabet[24] = 'Y';
        alphabet[25] = 'Z';
    }

    public String encode (String message, String code) {
        String encodedMessage = "";
        char [] codeAlphabet = createCodeAlphabet(code);
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (message.toUpperCase().charAt(i) == alphabet[j]) {
                    encodedMessage += codeAlphabet[j];
                }
            }
        }
        return encodedMessage;
    }

    public String decode (String message, String code) {
        String decodedMessage = "";
        char [] codeAlphabet = createCodeAlphabet(code);
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < codeAlphabet.length; j++) {
                if (message.toUpperCase().charAt(i) == codeAlphabet[j]) {
                    decodedMessage += alphabet[j];
                }
            }
        }
        return decodedMessage.toLowerCase();
    }

    private char[] createCodeAlphabet(String code) {
        code = removeDuplicates(code);
        for (char c : alphabet) {
            if (!code.contains(String.valueOf(c))) {
                code += c;
            }
        }
        return code.toCharArray();
    }

    private String removeDuplicates(String input) {
        String result = "";
        // Durchlaufe jedes Zeichen im Eingabestring
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            boolean isDuplicate = false;
            // Prüfe, ob das aktuelle Zeichen bereits im Ergebnisstring vorhanden ist
            for (int j = 0; j < result.length(); j++) {
                if (result.charAt(j) == currentChar) {
                    isDuplicate = true;
                    break;
                }
            }
            // Wenn das Zeichen kein Duplikat ist, füge es zum Ergebnisstring hinzu
            if (!isDuplicate) {
                result += currentChar;
            }
        }
        return result;
    }

}
