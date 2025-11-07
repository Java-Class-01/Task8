import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class MemberForm {
    JTextField nametextfield;
    JTextField emailField;
    JButton saveButton;
    JTable MemberTable;
    DefaultTableModel tableModel;
    JFrame Mainframe;
    JPanel Formpanel;
    JLabel namelabel,emaillabel;
    //constructor
    public MemberForm(){
        this.prepareJFrame();
    }
    public JFrame prepareJFrame(){
        Mainframe=new JFrame("GROUP ONE MEMBERS FORM");
        Mainframe.setSize(500, 400);
        Mainframe.setLayout(new BorderLayout(10, 10));
        Mainframe.add(this.prepareFormPanel(), BorderLayout.CENTER);
        Mainframe.add(this.prepareFormPanel());
        Mainframe.setVisible(true);

        return Mainframe;
    }
    //creating the panel to be added to our mainframe
    public JPanel prepareFormPanel(){
        Formpanel = new JPanel(new GridLayout(4, 2,10,10));
        Formpanel.add(this.prepareJLabelNamelabel());
        Formpanel.add(this.prepareJTextFieldNametextfield());
        Formpanel.add(this.prepareJLabelEmailLabel());
        Formpanel.add(this.prepareJTextFieldEmailTextField());
        Formpanel.add(this.prepareJButtonSaveButton());
        //putting the JTable in a scrollpane
        JScrollPane scrollPane = new JScrollPane(this.prepareMemberTable());
        Formpanel.add(scrollPane);
        return Formpanel;
    }
    //creating the JLabel method for the name and also the textfield
    public JLabel prepareJLabelNamelabel(){
        namelabel=new JLabel("your name");
        return namelabel;
    }
    public JTextField prepareJTextFieldNametextfield(){
        nametextfield=new JTextField();
        return nametextfield;
    }
    //creating the JLabel and JTextfield methods for the email
    public JLabel prepareJLabelEmailLabel(){
        emaillabel=new JLabel("your email");
        return emaillabel;
    }
    public JTextField prepareJTextFieldEmailTextField(){
        emailField=new JTextField();
        return emailField;
    }

}
