import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class AdminProgram extends JFrame
{
    private JPanel adminPanel;
    private JTextField textField1;
    private JButton loginButton;
    private JPasswordField passwordField1;
    public static Encryption encryption = new Encryption();

    public AdminProgram()
    {
        add(adminPanel);

        //Frame
        Image logo2 = new ImageIcon("FLOGO.png").getImage();
        setIconImage(logo2);
        setTitle("AcSys Admin System");
        setSize(920, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loginButton.setBackground(new Color(0xE22523));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                loginButton.setBackground(new Color(0xBF2321));
            }
        });

        loginButton.addActionListener(e -> {
            try {
                checkAdmin();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        getRootPane().setDefaultButton(loginButton);

    }

    private void checkAdmin() throws IOException {
        File adminFile = new File("admin.txt");
        if(adminFile.exists()) {
            try (FileReader fp = new FileReader("admin.txt")) {
                BufferedReader br = new BufferedReader(fp);
                String line = br.readLine();
                String[] data = line.split("\t");
                String adminUsername = decryptAdmin(data[0].trim());
                String adminPassword = decryptAdmin(data[1].trim());
                String usernameInp = textField1.getText();
                String passwordInp = new String(passwordField1.getPassword());

                if (!usernameInp.equals(adminUsername) || !adminPassword.equals(passwordInp)) {
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                    List.retrieveDB();
                    new dbUI();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
        {
            saveAdmin();
            dispose();
            List.retrieveDB();
            new dbUI();
        }
    }

    private void saveAdmin()
    {
        String username = encryptAdmin(textField1.getText());
        String password = encryptAdmin(new String(passwordField1.getPassword()));

        try(FileWriter fp2 = new FileWriter("admin.txt"))
        {
            fp2.write(username + "\t" + password);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private static String encryptAdmin(String n)
    {
        int len = n.length();
        int shift = 50;

        StringBuilder encryptedAdmin = new StringBuilder();

        for(int i = 0; i < len; i++)
        {
            char enChar, c;
            c = n.charAt(i);
            enChar = (char) (c + shift);
            encryptedAdmin.append(enChar);
        }
        return encryptedAdmin.toString();
    }

    private static String decryptAdmin(String n)
    {
        int len = n.length();
        int shift = 50;
        StringBuilder decryptedAdmin = new StringBuilder();

        for(int i = 0; i < len; i++)
        {
            char deChar, c;
            c = n.charAt(i);
            deChar = (char) (c - shift);
            decryptedAdmin.append(deChar);
        }
        return decryptedAdmin.toString();
    }

    public static void main(String[] args)
    {
        new Loading();
        encryption.newKey();
        new AdminProgram();
    }

}
