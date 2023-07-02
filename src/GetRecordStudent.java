import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GetRecordStudent extends JFrame{
    private JPanel panel1;
    private JTextField tfID;
    private JTextField tfCrs;
    private JTextField tfName;
    private JTextField tfYrSec;
    private JTextField tfContact;
    private JTextField tfAddress;
    private JButton registerIDButton;
    private JButton uploadAFileButton;
    private JButton cancelButton;
    private File idFile;
    private BufferedImage image;

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
        setUndecorated(true);
        setResizable(false);
        setVisible(true);

        //Buttons
        getRootPane().setDefaultButton(registerIDButton);
        registerIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStud();
            }
        });
        uploadAFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseID = new JFileChooser();
                int response = chooseID.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION)
                {
                    idFile = chooseID.getSelectedFile();
                    try {
                         image = ImageIO.read(idFile);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    uploadAFileButton.setEnabled(false);

                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        registerIDButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registerIDButton.setBackground(new Color(0x928685));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                registerIDButton.setBackground(new Color(0x8F2321));
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                cancelButton.setText("<html><u>Cancel</u><html>");
            }

            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                cancelButton.setText("Cancel");
            }
        });
    }

    private void registerStud()
    {
        Data r = new Data();
        boolean allFilled = true;

        r.name = tfName.getText();
        r.schoolID = tfID.getText();
        r.course = tfCrs.getText();
        r.yrSec = tfYrSec.getText();
        r.contactNum = tfContact.getText();
        r.address = tfAddress.getText();

        for(String fields : Arrays.asList(r.name, r.schoolID, r.course, r.yrSec, r.contactNum, r.address))
        {
            if(fields.isEmpty())
            {
                allFilled = false;
                break;
            }
        }

        if(allFilled)
        {
            if(!List.regCheck(r))
            {
                JOptionPane.showMessageDialog(null, "User already exist.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                dispose();
                new GetRecordStudent();
            }
            else
            {
                List.addRec(r);
                List.saveDB();
                List.saveToFD(r.schoolID);
                String newIDName = r.schoolID + ".png";
                saveImg(newIDName);
                JOptionPane.showMessageDialog(null, "Registered successfully!");
                dispose();
            }

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void saveImg(String studIDName)
    {
        try
        {
            File dir = new File("./ID Photos");
            if(!dir.exists())
            {
                dir.mkdirs();
            }
            File createFile = new File(dir, studIDName);
            ImageIO.write(image, "png", createFile);

        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Error saving image: " + ex.getMessage());
        }
    }

}
