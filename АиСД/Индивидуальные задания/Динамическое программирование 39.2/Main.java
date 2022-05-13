import java.io.*;

class Word {
    private String word;
    private String num;

    public Word(String word, String num) {
        this.word = word;
        this.num = num;
    }
}
class TelephoneNumber {
    private final String num;
    private final int n;
    private final Word[] words;
    private final String[] table = {
            "2", "2", "2", "3", "3", "3", "4", "4", "1", "1", "5", "5", "6",
            "6", "0", "7", "0", "7", "7", "8", "8", "8", "9", "9", "9", "0"
    };

    public TelephoneNumber() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        num = br.readLine();
        n = Integer.parseInt(br.readLine());
        words = new Word[n];
        String word;
        for(int i = 0; i < n; i++) {
            word = br.readLine();
            words[i] = new Word(word, decipher(word));
        }
    }

    private String decipher(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for(int i = 0; i < word.length(); i++) {
            if(Character.isDigit(word.charAt(i))) {
                sb.append(word.charAt(i));
            } else {
                sb.append(table[word.charAt(i) - 'A']);
            }
        }
        return sb.toString();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        TelephoneNumber tn = new TelephoneNumber();
    }
}
