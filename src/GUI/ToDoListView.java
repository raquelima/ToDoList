package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListView {

    private JFrame frame = new JFrame();
    private JPanel titlePanel  = new JPanel();
    private JPanel taskList = new JPanel();
    private JPanel buttonsPanel  = new JPanel();


    private Controller controller;

    private JTable table;
    private JScrollPane scrollP;

    private JLabel toDoList = new JLabel("TO-DO List");
    private JButton newTask = new JButton("   + New task   ");

    private JButton reset = new JButton("Reset");
    private JButton update = new JButton("Update");
    private JButton toDoneList = new JButton("to Done List ->");

    public ToDoListView(Controller controller) {
        this.controller = controller;

        addElements();
        loadTableModel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("To-Do List");
        frame.setSize(750, 500);
        frame.setVisible(true);
    }

    private void addElements() {
        //Layout
        frame.setLayout(new BorderLayout());
        titlePanel.setLayout(new BorderLayout());
        taskList.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new GridLayout(1,3,30,0));
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(taskList, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        //Elements
        TableCellRenderer tableRenderer;
        table = new JTable();
        tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JCheckBox.class, new JTableButtonRenderer(tableRenderer));
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        scrollP = new JScrollPane(table);
        taskList.add(scrollP, BorderLayout.CENTER);
        titlePanel.add(toDoList, BorderLayout.WEST);
        titlePanel.add(newTask, BorderLayout.EAST);
        buttonsPanel.add(reset);
        buttonsPanel.add(update);
        buttonsPanel.add(toDoneList);

        // Design
        toDoList.setFont(new Font("Arial",Font.PLAIN,35));
        newTask.setBackground(new Color(189,191,242));
        newTask.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        newTask.setOpaque(true);

        // Borders
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,60,0,60));
        taskList.setBorder(BorderFactory.createEmptyBorder(20,60,20,60));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,60,20,60));

        //Buttons
        newTask.addActionListener(e -> controller.setAddTaskViewVis());

        toDoneList.addActionListener(e -> controller.setDoneListViewVis());
    }

    private void loadTableModel() {
        AbstractTableModel model = controller.getModel();
        table.setModel(model);
    }

    public void DisposeView() {
        this.frame.dispose();
    }
}

class JTableButtonRenderer implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;
    public JTableButtonRenderer(TableCellRenderer renderer) {
        this.defaultRenderer = renderer;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Component)
            return (Component)value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}



