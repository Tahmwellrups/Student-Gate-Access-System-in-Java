import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Records extends JFrame{
    private JPanel panel1;
    private JButton btnExit;
    private JButton btnLogs;
    private JButton btnReg;
    private JButton btnHome;
    private JButton btnRec;
    private JTextArea textArea1;
    private JPanel recordPanel;

    public Records()
    {
        Node p = List.l;
        int i = 1;
        add(recordPanel);
        StringBuilder sb = new StringBuilder();
        while(p != null)
        {
            sb.append(i + ".| " + p.rec.name);
            sb.append("\t\t" + p.rec.course);
            sb.append("\t\t" + p.rec.yrSec);
            sb.append("\t\t" + p.rec.schoolID);
            sb.append("\t\t   " + p.rec.address);
            sb.append("\t\t" + p.rec.contactNum + "\n");
            textArea1.setText(sb.toString());
            p = p.next;
            i++;
        }

        //Frame
        Image logo2 = new ImageIcon("FLOGO.png").getImage();
        setIconImage(logo2);
        setTitle("AcSys Admin System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(true);
        setVisible(true);

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new dbUI();
            }
        });
        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Registration();
            }
        });
        btnLogs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new DateLogs().setVisible(true));
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnHome.setBackground(new Color(92, 86, 85));

            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnHome.setBackground(new Color(20, 20, 20));
            }
        });
        btnReg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnReg.setBackground(new Color(92, 86, 85));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnReg.setBackground(new Color(20, 20, 20));
            }
        });

        btnLogs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnLogs.setBackground(new Color(92, 86, 85));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnLogs.setBackground(new Color(20, 20, 20));
            }
        });
        btnRec.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnRec.setBackground(new Color(92, 86, 85));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnRec.setBackground(new Color(20, 20, 20));
            }
        });
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnExit.setBackground(new Color(92, 86, 85));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnExit.setBackground(new Color(20, 20, 20));
            }
        });
    }
}
