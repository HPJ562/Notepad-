import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener {
    
    JTextArea t;

    JFrame f;
    // frame
//  constructor
    editor()
    {
        f = new JFrame("editor");

        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }
        t = new JTextArea();
        //creating a menubar
        JMenuBar mb=new JMenuBar();

        //creating a flie menu 
        JMenu m1= new JMenu("File");

        //creating menu items

        JMenuItem mi1=new JMenuItem("New");
        JMenuItem mi2=new JMenuItem("Open");
        JMenuItem mi3=new JMenuItem("Save");
        JMenuItem mi9=new JMenuItem("Print");

        // Add action Listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        //creating a edit menu
        JMenu m2=new JMenu("Edit");

        //create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        //add action listener

        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        //create a menu for close
        JMenuItem mc = new JMenuItem("close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }


    
    // when button is pressed
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("cut")){
            t.cut();
        }
        else if(s.equals("copy")){
            t.copy();
        }
        else if(s.equals("paste")){
            t.paste();
        }
        else if(s.equals("Save")){

            //create an object of jFilechooser class
            JFileChooser j = new JFileChooser("f:");
            
            //to show the save dialog
            int r = j.showSaveDialog(null);
            if(r== JFileChooser.APPROVE_OPTION){
                //label to the path of selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try{
                    //create a file writer
                    FileWriter wr = new FileWriter(fi, false);
                    //create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);
                    // write
                    w.write(t.getText());
                    w.flush();
                    w.close();
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            //if the user cancelled the operation
            else
            JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        }
        else if(s.equals("Print")){
            try{
                //print the file
                t.print();
            }
            catch(Exception evt){
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if(s.equals("Open")){
            //Create an object of jfilechooser cls
            JFileChooser j = new JFileChooser("f:");

            // show the dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if(r == JFileChooser.APPROVE_OPTION){
                //select the label path directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    
                    String s1 = "",sl="";

                    // File reader
                    FileReader fr = new FileReader(fi);
                    
                    //buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    //initialize sl
                    sl = br.readLine();

                    // take the input from the file
                    while((s1=br.readLine())!=null){
                        sl = sl+"\n" + s1;
                    }
                    //set the text
                    t.setText(sl);
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // if user canceled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        }
        else if (s.equals("New")){
            t.setText("");
        }
        else if (s.equals("Close")){
            f.setVisible(false);
        }
    }

   public static void main(String args[])
	{
		editor e = new editor();

    }
}