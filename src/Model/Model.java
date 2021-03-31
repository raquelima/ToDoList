package Model;

import Data.RowData;
import Data.TaskData;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Model {

    private ArrayList<TaskData> tasks = new ArrayList<>();

    private ArrayList<RowData> rows = new ArrayList<>();

    public void newTask(TaskData task, RowData row) {
        tasks.add(task);
        rows.add(row);
    }

//    public ArrayList getArray() {
//        return tasks;
//    }

//    public Object[][] getRows() {
//        Object[][] bro = {rows.toArray()};
//        return bro;
//    }

    public Model() {
        JButton s = new JButton("löschen");
        s.addActionListener((e) ->{
            System.out.println("test lima");
        });
        rows.add(new RowData("kekw",new JCheckBox("yeah"),s,new JButton("edit"),new JButton("details")));
    }

    public AbstractTableModel getModel() {

        String[] reihen = new String[]{"Titel", "Checkbox", "Delete", "Edit", "Details"};

        return new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return rows.size();
            }

            @Override
            public int getColumnCount() {
                return reihen.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return rows.get(rowIndex).getAsArray()[columnIndex];
            }

            public String getColumnName(int column) {
                return reihen[column];
            }
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }


        };
    }
}
