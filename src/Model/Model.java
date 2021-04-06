package Model;

import Data.RowData;
import Data.TaskData;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Model {

    private ArrayList<TaskData> tasks = new ArrayList<>();

    private ArrayList<RowData> rows = new ArrayList<>();

    private String value;

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

    }

    public int length(){
        return (rows.size() < 0)? rows.size()-1 : 0;
    }

    public void deleteTask(int length){
        rows.remove(length);
    }

    public AbstractTableModel getModel() {

        String[] reihen = new String[]{"Title", "Checkbox", "Delete", "Details", "Edit"};

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

    public String getDetails(int index, int entry){

        TaskData user = tasks.get(index);

        switch (entry) {
            case 1:
                value = user.getTitle();
                break;
            case 2:
                value = user.getDueDate();
                break;
            case 3:
                value = String.valueOf(user.getPriority());
                break;
            case 4:
                value = user.getDescription();
                break;
        }
        return value;
    }
}
