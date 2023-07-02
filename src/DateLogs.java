import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class DateLogs extends JFrame
{
    private final JLabel SpecificDate;
    private final JLabel Clock;
    private JPanel DateLogs_MainFrame;
    private JPanel JCalendar;
    private JButton getButton;
    private JButton backButton;
    private JTextArea textArea;
    Calendar cld = Calendar.getInstance();
    JDateChooser DatePicker = new JDateChooser (cld.getTime());
    ClockThread clockThread = new ClockThread();

    public DateLogs()
    {
        //FRAME
        Image logo2 = new ImageIcon("FLOGO.png").getImage();
        setIconImage(logo2);
        setTitle("AcSys Admin System");
        setSize(920, 639);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);

        //CLOCK LABEL
        Clock = new JLabel();
        Clock.setFont(new Font("Glacial Indifference", Font.PLAIN, 12));
        Clock.setForeground(new Color(0xECEFDA));
        Clock.setBounds(140, 180, 100,100);

        //SPECIFIC DATE LABEL
        SpecificDate = new JLabel();
        SpecificDate.setFont(new Font("Glacial Indifference", Font.PLAIN, 12));
        SpecificDate.setForeground(new Color(0xECEFDA));
        SpecificDate.setBounds(125, 195, 200,100);
        updateDateLabel();
        Timer timer = new Timer(1000, e -> updateDateLabel());
        timer.start();

        //ADDING OF FUNCTIONS
        add(SpecificDate);
        add(Clock);
        add(DateLogs_MainFrame);

        //CALLING OF CLOCK THREAD
        clockThread.start();

        //CALENDAR PICKER
        DatePicker.setDateFormatString("MM-dd-yyyy");
        JCalendar.add(DatePicker);

        //BUTTONS
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdfmt = new SimpleDateFormat("MM-dd-yyyy");
                String date = sdfmt.format(DatePicker.getDate());
                DataLogs_List.clear();
                DataLogs_List.retrieveLG(date);
                DisplayLogs();
            }
        });

        getButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                getButton.setBackground(new Color(0x928685));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                getButton.setBackground(new Color(0x8F2321));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new dbUI();
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backButton.setText("<html><u>Back</u><html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                backButton.setText("Back");
            }
        });

    }

    //FUNCTIONS
    private void DisplayLogs()
    {
        DataLogs_Node p = DataLogs_List.a;
        StringBuilder sb = new StringBuilder();

        while (p != null) {
            sb.append("  " + p.Logs_rec.schoolID);
            sb.append("\t" + p.Logs_rec.date);
            sb.append("\t\t" + p.Logs_rec.time);
            sb.append("\t\t" + p.Logs_rec.time_type + "\n");
            textArea.setText(sb.toString());
            p = p.Logs_next;
        }
    }

    private class ClockThread extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                SimpleDateFormat clockFormat = new SimpleDateFormat("hh:mm:ss a");
                String time = clockFormat.format(new Date());

                SwingUtilities.invokeLater(() -> Clock.setText(time));

                try
                {
                    Thread.sleep(1000); // Wait for 1 second
                }

                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateDateLabel ()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EE, MMM dd, yyyy");
        Date currentDate = new Date();
        SpecificDate.setText(dateFormat.format(currentDate));
    }

}
