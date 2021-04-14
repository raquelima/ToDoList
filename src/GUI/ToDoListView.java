package GUI;

import Controller.Controller;
import Data.RowData;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToDoListView {

    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel taskList = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private Controller controller;

    private JTable table;
    private JScrollPane scrollP;

    ImageIcon image = new ImageIcon("src/Images/generatedtext.png");
    private JLabel toDoList = new JLabel(image);
    ImageIcon image2 = new ImageIcon("src/Images/addIcon.png");
    private JButton newTask = new JButton(image2);

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
        buttonsPanel.setLayout(new GridLayout(1, 3, 30, 0));
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(taskList, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        //Elements
        TableCellRenderer tableRenderer;
        table = new JTable();
        tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JCheckBox.class, new JTableButtonRenderer(tableRenderer));
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        table.getTableHeader().setReorderingAllowed(false);

        scrollP = new JScrollPane(table);
        taskList.add(scrollP, BorderLayout.CENTER);
        titlePanel.add(toDoList, BorderLayout.WEST);
        titlePanel.add(newTask, BorderLayout.EAST);
        buttonsPanel.add(reset);
        buttonsPanel.add(update);
        buttonsPanel.add(toDoneList);

        // Design
        newTask.setOpaque(false);
        newTask.setContentAreaFilled(false);
        newTask.setBorderPainted(false);
        titlePanel.setBackground(new Color(255, 201, 92));
        buttonsPanel.setBackground(new Color(112,193,179));
        taskList.setBackground(new Color(112,193,179));

        // Borders
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 0, 60));
        taskList.setBorder(BorderFactory.createEmptyBorder(20, 60, 0, 60));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        //Buttons
        newTask.addActionListener(e -> controller.setAddTaskViewVis());
        toDoneList.addActionListener(e -> controller.setDoneListViewVis());
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //default icon, custom title
                Object[] options = {"Yes, please",
                        "No way!"};
                int n = JOptionPane.showOptionDialog(frame,
                        "Are you sure you would like to reset your To-Do List?",
                        "A Silly Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == JOptionPane.YES_OPTION){
                    controller.deleteAllTasks();
                    controller.setToDoListViewVis();
                }

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (RowData i: controller.getAllRows()) {
                    if (i.getCheckBox().isSelected())
                    {
                        controller.getDoneTasksRow().add(i);

                    }
                }

                controller.getAllRows().removeAll(controller.getDoneTasksRow());
                controller.setToDoListViewVis();

            }
        });
    }

    private void loadTableModel() {
        AbstractTableModel model = controller.getModel();
        table.setModel(model);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable t = (JTable) e.getComponent();
                int columindex = t.columnAtPoint(e.getPoint());
                int rowindex = t.rowAtPoint(e.getPoint());
                Object cell = table.getModel().getValueAt(rowindex, columindex);
                if (cell instanceof JButton) {
                    JButton b = (JButton) table.getModel().getValueAt(rowindex, columindex);

                    if (b.getText() == "Delete"){
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                controller.deleteTask(rowindex);
                                controller.setToDoListViewVis();
                            }
                        });
                    }
                    if (b.getText() == "Details"){
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                controller.setDetailViewVis(rowindex);
                            }
                        });
                    }
                    if (b.getText() == "Edit"){
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                controller.setEditTaskVis(rowindex);
                            }
                        });
                    }
                    b.doClick();
                }
                if (cell instanceof JCheckBox){
                    JCheckBox c = (JCheckBox) table.getModel().getValueAt(rowindex, columindex);
                    boolean checker = true;
                    if (!c.isSelected()){
                        checker = true;
                    }
                    if(c.isSelected()){
                        checker = false;
                    }
                    c.setSelected(checker);
                    controller.setToDoListViewVis();

                }
                super.mouseClicked(e);
            }
        });

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
        if (value instanceof Component)
            return (Component) value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}






