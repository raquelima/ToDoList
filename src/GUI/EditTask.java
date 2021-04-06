package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EditTask extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel formular = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private Controller controller;

    private JLabel editTask = new JLabel("Edit Task");
    private JLabel title = new JLabel("Title*:", JLabel.LEFT);
    private JLabel dueDate = new JLabel("Due Date:");
    private JLabel priority = new JLabel("Priority:");
    private JLabel description = new JLabel("Description*:");

    private JTextField titleF = new JTextField("");
    private JTextField dueDateF = new JTextField("");
    private JTextField priorityF = new JTextField("");
    private JTextField descriptionF = new JTextField("");

    private JButton cancel = new JButton("Cancel");
    private JButton save = new JButton("Save");

    public EditTask(Controller controller) {
        this.controller = controller;

        addElements();

        frame.setTitle("Edit Task");
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    private void addElements(){
        // Layout
        frame.setLayout(new BorderLayout());
        formular.setLayout(new GridLayout(4,2,5,10));
        titlePanel.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridLayout(1,2));
        frame.add(formular, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        // Elements
        titlePanel.add(editTask);
        formular.add(title);
        formular.add(titleF);
        formular.add(dueDate);
        formular.add(dueDateF);
        formular.add(priority);
        formular.add(priorityF);
        formular.add(description);
        formular.add(descriptionF);
        buttonsPanel.add(cancel);
        buttonsPanel.add(save);

        // Design
        editTask.setFont(new Font("Arial",Font.PLAIN,30));
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
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,40,20,40));

        //ActionListeners
        cancel.addActionListener(e -> controller.setToDoListViewVis());
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
