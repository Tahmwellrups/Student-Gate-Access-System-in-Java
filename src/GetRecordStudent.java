import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetRecordStudent extends JFrame{
    private JPanel panel1;
    private JTextField tfID;
    private JTextField tfCrs;
    private JTextField tfName;
    private JTextField tfYrSec;
    private JTextField tfContact;
    private JTextField tfAddress;
    private JButton registerIDButton;


    public GetRecordStudent()
    {
        add(panel1);
        //Frame
        Image logo2 = new ImageIcon("FLOGO.png").getImage();
        setIconImage(logo2);
        setTitle("AcSys Admin System");
        setSize(920, 639);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        registerIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStud();
                JOptionPane.showMessageDialog(null, "Registered successfully!");
                dispose();
                new Registration();
            }
        });
        getRootPane().setDefaultButton(registerIDButton);
    }

    private void registerStud()
    {
        Data r = new Data();

        r.name = tfName.getText();
        r.schoolID = tfID.getText();
        r.course = tfCrs.getText();
        r.yrSec = tfYrSec.getText();
        r.contactNum = tfContact.getText();
        r.address = tfAddress.getText();

        List.addRec(r);
        List.saveDB();
    }
}
