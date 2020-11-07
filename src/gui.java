//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  

class gui {
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Search Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");//
        JMenu m2 = new JMenu("Help");//use this to open README
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        
        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter item number:");
        JTextField tf = new JTextField(20); // accepts upto 10 characters
        JButton search = new JButton("Search");
        JButton reset = new JButton("Reset");
        
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(search);
        panel.add(reset);
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
        
        
        search.addActionListener(new ActionListener(){  
           public void actionPerformed(ActionEvent e){  
           String resoult = "Item: " + tf.getText() + "\nPO#: \npcs: \nContainer NO/ seal: \nETA: ";
                  ta.setFont(new Font("Ariel", Font.PLAIN, 18));
                  ta.setText(resoult);  
           }  
           });  

        
    }
    
    
}