package GUI;

import Controller.Controller;
import Data.RowData;
import Data.TaskData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class AddTask {
    private JTableButtonModel t = new JTableButtonModel();
    private JFrame frame = new JFrame();
    private JPanel formular = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private JLabel newTask = new JLabel("New Task");
    private JLabel title = new JLabel("Title*:", JLabel.LEFT);
    private JLabel dueDate = new JLabel("Due Date:");
    private JLabel priority = new JLabel("Priority:");
    private JLabel description = new JLabel("Description*:");

    //Field with date format
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uu");
    DateField dateField = new DateField(dateFormatter);

    private JTextField titleF = new JTextField("");
    private JTextField priorityF = new JTextField("");
    private JTextField descriptionF = new JTextField("");

    private JButton cancel = new JButton("Cancel");
    private JButton add = new JButton("Add");

    public AddTask(Controller controller) {
        // Layout
        frame.setLayout(new BorderLayout());
        formular.setLayout(new GridLayout(4,2,5,10));
        titlePanel.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridLayout(1,2));
        frame.add(formular, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        // Elements
        titlePanel.add(newTask);
        formular.add(title);
        formular.add(titleF);
        formular.add(dueDate);
        formular.add(dateField);
        dateField.setValue(LocalDate.now(ZoneId.systemDefault()));
        formular.add(priority);
        formular.add(priorityF);
        formular.add(description);
        formular.add(descriptionF);
        buttonsPanel.add(cancel);
        buttonsPanel.add(add);

        // Design
        newTask.setFont(new Font("Arial",Font.PLAIN,30));
        title.setBackground(new Color(189,191,242));
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        title.setOpaque(true);
        dueDate.setBackground(new Color(189,191,242));
        dueDate.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        dueDate.setOpaque(true);
        priority.setBackground(new Color(189,191,242));
        priority.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        priority.setOpaque(true);
        description.setBackground(new Color(189,191,242));
        description.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        description.setOpaque(true);

        // Borders
        formular.setBorder(BorderFactory.createEmptyBorder(0,40,10,40));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,40,10,40));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,40,0,40));

        //ActionListeners
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setToDoListViewVis();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(titleF.getText().equals("") || descriptionF.getText().equals("")) {
                   JOptionPane.showMessageDialog(null, "Please fill all of the required fields", "Field required", JOptionPane.ERROR_MESSAGE);
                } else {
                    JCheckBox checkbox = new JCheckBox();
                    JButton delete = new JButton();
                    JButton details = new JButton();
                    JButton edit = new JButton();

                    TaskData data = new TaskData(titleF.getText(), dateField.getText(), Integer.parseInt(priorityF.getText()), descriptionF.getText());
                    RowData row = new RowData(titleF.getText(), checkbox, delete, details, edit );
                    controller.newTask(data, row);
                    controller.setToDoListViewVis();
                }
            }
        });

        //Window Settings
        frame.setTitle("Add New Task");
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

}

class DateField extends JFormattedTextField {

    private static final long serialVersionUID = -4070878851012651987L;

    public DateField(DateTimeFormatter dateFormatter) {
        super(dateFormatter.toFormat(LocalDate::from));
        setPreferredSize(new Dimension(100, 26));
    }

    @Override
    public LocalDate getValue() {
        return (LocalDate) super.getValue();
    }

}

