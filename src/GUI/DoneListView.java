package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DoneListView extends JFrame implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel titlePanel  = new JPanel();
    private JPanel taskList = new JPanel();
    private JPanel buttonsPanel  = new JPanel();

    private String[] columns = {"Title", "Completed", "Buttons"};
    private String[][] rows = {{"Hausaufgaben", "Checkbox", "Details Delete"},{"Hausaufgaben", "Checkbox", "Details Delete"},{"Hausaufgaben", "Checkbox", "Details Delete"}};

    private JTable jt = new JTable(rows,columns);
    private DefaultTableModel dm = new DefaultTableModel();

    private JLabel toDoList = new JLabel("Done List");

    private JButton reset = new JButton("Reset");
    private JButton update = new JButton("Update");
    private JButton toToDoList = new JButton("to To-Do List ->");

    private JScrollPane sp = new JScrollPane(jt);

    public static void main(String[] args) {
        DoneListView a = new DoneListView();
    }

    public DoneListView() {
        //Layout
        frame.setLayout(new BorderLayout());
        titlePanel.setLayout(new BorderLayout());
        taskList.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridLayout(1,3,30,0));
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(taskList, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        jt.setEnabled(false);

        //Elements
        titlePanel.add(toDoList, BorderLayout.WEST);
        taskList.add(sp);
        buttonsPanel.add(reset);
        buttonsPanel.add(update);
        buttonsPanel.add(toToDoList);

        // Design
        toDoList.setFont(new Font("Arial",Font.PLAIN,35));

        // Borders
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,60,0,60));
        taskList.setBorder(BorderFactory.createEmptyBorder(20,60,20,60));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,60,20,60));

        //Buttons
        toToDoList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ToDoListView t = new ToDoListView();
            }
        });

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Done List");
        frame.setSize(750, 500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}

