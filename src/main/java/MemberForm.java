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
    //adding the JButton method and its action listener when the save button is clicked
    public JButton prepareJButtonSaveButton(){
        saveButton=new JButton("save your details");

        //action handler for saving when the save details button is clicked by member
        saveButton.addActionListener(e -> {
            String name = nametextfield.getText().trim();
            String email = emailField.getText().trim();
            //if statement to not take empty values
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            if (saveUser(name, email)) {
                JOptionPane.showMessageDialog(null, "User saved successfully!");
                loadUsers();
                nametextfield.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error saving user!");
            }
        });

        return saveButton;
    }
    //creating the table in the database to contain the member details
    public JTable prepareMemberTable(){
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        loadUsers();
        MemberTable=new JTable(tableModel);
        return MemberTable;
    }
   //method to get the details of members from database and display them on JTable
    private void loadUsers() {
        tableModel.setRowCount(0);
        String sql = "SELECT id, name, email FROM Users";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                tableModel.addRow(new Object[]{id, name, email});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }}

    //saving the entered member details to the database
    private boolean saveUser(String name, String email) {
        String sql = "INSERT INTO Users (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
