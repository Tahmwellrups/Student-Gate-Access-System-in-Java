import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Encryption {

    private ArrayList<Character> list = new ArrayList<>();
    private ArrayList<Character> shuffledList = new ArrayList<>();
    private char[] letters;
    private Scanner scan = new Scanner(System.in);
    int key;

    public String encrypt(String data) {
        StringBuilder encrypted = new StringBuilder();
        letters = data.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }
        
        for (char x : letters) {
            key = key % 95;
            x += key;
            encrypted.append(x);
        }
        
        return encrypted.toString();
    }

    public void newKey() {

        char character = ' ';

        for (int i = 32; i < 127; i++) {
            list.add(Character.valueOf(character));
            character++;
        }

        shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);

        // Save sa file
        String input = JOptionPane.showInputDialog(null, "Enter Encryption Key: ");
        key = Integer.parseInt(input);
        StringBuilder content = new StringBuilder();
        content.append(key);

        for (Character x : shuffledList) {
            content.append(x);
        }

        String result = content.toString();

        try {
            FileWriter fp = new FileWriter("key.tj");
            fp.write(result);
            fp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
