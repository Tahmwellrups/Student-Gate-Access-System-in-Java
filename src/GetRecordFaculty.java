import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetRecordFaculty extends JFrame{
    private JTextField tfID;
    private JTextField tfCol;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfDept;
    private JTextField tfContact;
    private JButton registerIDButton;
    private JPanel facPanel;

    public GetRecordFaculty()
    {
        add(facPanel);
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
                registerFac();
                JOptionPane.showMessageDialog(null, "Registered successfully!");
                dispose();
                new Registration();
            }
        });
        getRootPane().setDefaultButton(registerIDButton);
    }

    private void registerFac()
    {
        Data r = new Data();

        r.name = tfName.getText();
        r.schoolID = tfID.getText();
        r.course = tfCol.getText();
        r.yrSec = tfDept.getText();
        r.contactNum = tfContact.getText();
        r.address = tfAddress.getText();

        List.addRec(r);
        List.saveDB();
    }
}
