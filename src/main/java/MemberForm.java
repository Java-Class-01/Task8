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

}
