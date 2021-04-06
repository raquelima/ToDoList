package GUI;

import Controller.Controller;
import Data.TaskData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailView extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel formular = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private Controller controller;

    private JLabel newTask = new JLabel("");
    private JLabel title = new JLabel("Title*:", JLabel.LEFT);
    private JLabel dueDate = new JLabel("Due Date:");
    private JLabel priority = new JLabel("Priority:");
    private JLabel description = new JLabel("Description*:");

    private JTextField titleF = new JTextField("");
    private JTextField dueDateF = new JTextField("");
    private JTextField priorityF = new JTextField("");
    private JTextField descriptionF = new JTextField("");

    private JButton back = new JButton("Back to list");

    public DetailView(Controller controller, int index) {
        this.controller = controller;

        addElements(index);

        //Window Settings
        frame.setTitle("Detail View");
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    private void addElements(int index){
        // Layout
        frame.setLayout(new BorderLayout());
        formular.setLayout(new GridLayout(4,2,5,10));
        titlePanel.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new BorderLayout());
        frame.add(formular, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        // Elements
        TaskData task = controller.getTaskDetails(index);

        titlePanel.add(newTask);
        newTask.setText(task.getTitle());
        formular.add(title);
        formular.add(titleF);
        titleF.setText(task.getTitle());
        titleF.setEditable(false);
        formular.add(dueDate);
        formular.add(dueDateF);
        dueDateF.setText(task.getDueDate());
        dueDateF.setEditable(false);
        formular.add(priority);
        formular.add(priorityF);
        priorityF.setText(String.valueOf(task.getPriority()));
        priorityF.setEditable(false);
        formular.add(description);
        formular.add(descriptionF);
        descriptionF.setText(task.getDescription());
        descriptionF.setEditable(false);
        buttonsPanel.add(back);

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

        //Button
        back.addActionListener(e -> this.frame.dispose());

    }

    public void DisposeView() {
        this.frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
