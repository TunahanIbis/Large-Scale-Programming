import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Worksheet1 {
    private JLabel lblID, lblGPA;
    private JTextField txtID, txtGPA;
    private JButton btnSubmit;

    public Worksheet1() {
        // set layout
        JFrame frame = new JFrame("Student Information");
        frame.setLayout(new GridLayout(3, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize GUI items
        lblID = new JLabel("ID");
        lblGPA = new JLabel("GPA");

        txtID = new JTextField();
        txtGPA = new JTextField();

        btnSubmit = new JButton("Submit");

        frame.add(lblID);
        frame.add(txtID);
        frame.add(lblGPA);
        frame.add(txtGPA);
        frame.add(btnSubmit);

        frame.setSize(400, 200);
        frame.setVisible(true);

        // register buttons to respond actions
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // read contents of the objects
                String ID = txtID.getText();
                String GPA = txtGPA.getText();

                try {
                    int gpa = Integer.parseInt(GPA);

                    if (ID.length() != 10) {
                        JOptionPane.showMessageDialog(frame, "ID length must be equal to 10.");
                    } else if (gpa < 0 || gpa > 4) {
                        JOptionPane.showMessageDialog(frame, "GPA must be between 0 and 4.");
                    } else {
                        StudentInfo student = new StudentInfo(ID, gpa);
                        JOptionPane.showMessageDialog(frame, "Student Info: " + student.toString());
                        // Resetting the text fields
                        txtID.setText("");
                        txtGPA.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Numeric values for ID and GPA are required.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new Worksheet1();
    }
}

class StudentInfo {
    private String ID;
    private int GPA;

    public StudentInfo(String ID, int GPA) {
        this.ID = ID;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "ID: " + ID + " GPA: " + GPA;
    }
}
