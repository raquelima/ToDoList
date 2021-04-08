package GUI;

import Controller.Controller;
import Data.RowData;
import Data.TaskData;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AddTask {

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

    private String[] priorityF = {"1", "2", "3"};
    private JComboBox priorities = new JComboBox(priorityF);

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
        formular.add(priorities);
        formular.add(description);
        formular.add(descriptionF);
        buttonsPanel.add(cancel);
        buttonsPanel.add(add);

        // Design
        newTask.setFont(new Font("",Font.PLAIN,30));
        newTask.setForeground((new Color(145, 129, 225)));
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
        titlePanel.setBackground(new Color(255, 201, 92));
        formular.setBackground(new Color(112,193,179));
        buttonsPanel.setBackground(new Color(112,193,179));

        // Borders
        formular.setBorder(BorderFactory.createEmptyBorder(10,40,15,40));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,40,10,40));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,40,20,40));

        //ActionListeners
        cancel.addActionListener(e -> this.frame.dispose());
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(titleF.getText().equals("") || descriptionF.getText().equals("")) {
                   JOptionPane.showMessageDialog(null, "Please fill all of the required fields", "Field required", JOptionPane.ERROR_MESSAGE);
                    if(titleF.getText().equals("")) {
                        titleF.setBackground(new Color(255,105,97)); }
                    if(descriptionF.getText().equals("")) {
                        descriptionF.setBackground(new Color(255,105,97));}
                } else {
                    JCheckBox checkbox = new JCheckBox();

                    JButton delete = new JButton("Delete");
                    /*delete.setOpaque(true);
                    delete.setBackground(Color.DARK_GRAY);
                    delete.repaint();*/

                    JButton details = new JButton("Details");
                    /*details.setOpaque(true);
                    details.setBackground(Color.DARK_GRAY);
                    details.repaint();*/

                    JButton edit = new JButton("Edit");
                    /*edit.setOpaque(true);
                    edit.setBackground(Color.DARK_GRAY);
                    edit.repaint();*/

                    TaskData data = new TaskData(titleF.getText(), dateField.getText(), Integer.parseInt((String) priorities.getSelectedItem()), descriptionF.getText());
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

    public void DisposeView() {
        this.frame.dispose();
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

