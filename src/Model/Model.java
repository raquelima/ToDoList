package Model;

import Data.RowData;
import Data.TaskData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Model {

    private ArrayList<TaskData> tasks = new ArrayList<>();

    private ArrayList<RowData> rows = new ArrayList<>();

    private ArrayList<RowData> doneTasksRow = new ArrayList<>();


    public void newTask(TaskData task, RowData row) {
        tasks.add(task);
        rows.add(row);
    }

    public Model() {

    }

    public void deleteTask(int length){
        rows.remove(length);
        tasks.remove(length);
    }

    public void deleteDoneTask(int length){
        doneTasksRow.remove(length);
    }

    public void deleteAllTasks(){
        rows.clear();
        tasks.clear();
        doneTasksRow.clear();
    }

    public ArrayList<RowData> getAllRows(){
        return rows;
    }

    public ArrayList<RowData> getDoneTasksRow(){
        return doneTasksRow;
    }

    public TaskData getTaskDetails(int index){
        return tasks.get(index);
    }

    public RowData getRowDetails(int index){
        return rows.get(index);
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

    public AbstractTableModel getDoneModel() {

        String[] reihen = new String[]{"Title", "Checkbox", "Delete", "Details"};

        return new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return doneTasksRow.size();
            }

            @Override
            public int getColumnCount() {
                return reihen.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return doneTasksRow.get(rowIndex).getAsArray()[columnIndex];
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
