
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui extends JFrame {

	private JTextArea chatWindow;
	
    private JLabel statusbar;
    private JPanel panel;

    public Gui() {

        initUI();
    }

    public final void initUI() {

        panel = new JPanel();
        statusbar = new JLabel(" HI!");


        panel.setLayout(null);

        // Initialize buttons
        JButton sendMsg = new JButton("Send Msg");
        JButton sendFile = new JButton("Send File");
        JButton what = new JButton("What are you looking at");
        JButton no = new JButton("Don't click me");
        
        // Set button borders
        sendMsg.setBounds(40, 80, 200, 25);
        sendFile.setBounds(40, 130, 200, 25);
        what.setBounds(40, 180, 200, 25);
        no.setBounds(40, 230, 200, 25);
        
        sendMsg.addActionListener(new ButtonListener());
        sendFile.addActionListener(new ButtonListener());
        what.addActionListener(new ButtonListener());
        no.addActionListener(new ButtonListener());

        panel.add(sendMsg);
        panel.add(sendFile);
        panel.add(what);
        panel.add(no);

        add(panel);
        add(statusbar, BorderLayout.SOUTH);

        setTitle("Multiple");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
    }

    // Create a new custom listener that is an inner class
    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton myButton = (JButton) e.getSource();
            String label = myButton.getText();
            statusbar.setText(" " + label + " button clicked");
        }
    }

    public static void main(String[] args) {

    	Gui gui = new Gui();

    }
}
