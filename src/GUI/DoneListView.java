package GUI;

import Controller.Controller;
import Data.RowData;
import Data.TaskData;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoneListView {

    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel taskList = new JPanel();
    private JPanel buttonsPanel = new JPanel();


    private Controller controller;

    private JTable table;
    private JScrollPane scrollP;

    private JLabel toDoList = new JLabel("Done-List");
    private JButton newTask = new JButton("   + New task   ");

    private JButton reset = new JButton("Reset");
    private JButton update = new JButton("Update");
    private JButton toToDoList = new JButton("to To-Do List ->");

    public DoneListView(Controller controller) {
        this.controller = controller;

        addElements();
        loadTableModel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Done-List");
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


        scrollP = new JScrollPane(table);
        taskList.add(scrollP, BorderLayout.CENTER);
        titlePanel.add(toDoList, BorderLayout.WEST);
        titlePanel.add(newTask, BorderLayout.EAST);
        buttonsPanel.add(reset);
        buttonsPanel.add(update);
        buttonsPanel.add(toToDoList);

        // Design
        toDoList.setFont(new Font("Arial", Font.PLAIN, 35));
        newTask.setVisible(false);

        // Borders
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 0, 60));
        taskList.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 20, 60));

        //Buttons
        toToDoList.addActionListener(e -> controller.setToDoListViewVis());
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteAllTasks();
                controller.setDoneListViewVis();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (RowData i: controller.getDoneTasksRow()) {
                    if (!i.getCheckBox().isSelected())
                    {
                        controller.getAllRows().add(i);
                    }
                }

                controller.getDoneTasksRow().removeAll(controller.getAllRows());
                controller.setDoneListViewVis();

            }
        });
    }

    private void loadTableModel() {
        AbstractTableModel model = controller.getDoneModel();
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
                                controller.deleteDoneTask(rowindex);
                                controller.setDoneListViewVis();
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
                    b.doClick();
                }
                if (cell instanceof JCheckBox){
                    JCheckBox c = (JCheckBox) table.getModel().getValueAt(rowindex, columindex);
                    c.setSelected(false);
                    controller.setDoneListViewVis();
                }
                super.mouseClicked(e);
            }
        });

    }

    public void DisposeView() {
        this.frame.dispose();
    }
}


