import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradingSystem extends JFrame implements ActionListener {

    JTextField nameField, scoreField;
    JButton addButton, calculateButton;
    JTable table;
    DefaultTableModel tableModel;

    int totalScore = 0;
    int totalStudents = 0;

    public StudentGradingSystem() {
        setTitle("Student Grading System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nameField = new JTextField(10);
        scoreField = new JTextField(10);
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");
        tableModel.addColumn("Grade");

        table = new JTable(tableModel);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Score:"));
        inputPanel.add(scoreField);
        inputPanel.add(addButton);
        inputPanel.add(calculateButton);

        JScrollPane scrollPane = new JScrollPane(table);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addStudent();
        } else if (e.getSource() == calculateButton) {
            calculateAverage();
        }
    }

    void addStudent() {
        String name = nameField.getText();
        String scoreText = scoreField.getText();
        int score;

        try {
            score = Integer.parseInt(scoreText);
            if (score < 0 || score > 100) {
                JOptionPane.showMessageDialog(this, "Invalid score! Please enter a score between 0 and 100.",
                        "Invalid Score", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid score! Please enter a valid number.",
                    "Invalid Score", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String grade = assignGrade(score);

        Object[] rowData = {name, score, grade};
        tableModel.addRow(rowData);

        totalScore += score;
        totalStudents++;

        nameField.setText("");
        scoreField.setText("");
    }

    private String assignGrade(int score) {
        if (score >= 90 && score <= 100) {
            return "A";
        } else if (score >= 80 && score <= 89) {
            return "B";
        } else if (score >= 70 && score <= 79) {
            return "C";
        } else if (score >= 60 && score <= 69) {
            return "D";
        } else {
            return "F";
        }
    }

    void calculateAverage() {
        if (totalStudents == 0) {
            JOptionPane.showMessageDialog(this, "No students added.", "No Students", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double average = (double) totalScore / totalStudents;

        JOptionPane.showMessageDialog(this, "Average Score: " + average, "Average Score", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        StudentGradingSystem gradingSystem = new StudentGradingSystem();
        gradingSystem.setVisible(true);
    }
}
