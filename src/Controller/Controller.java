package Controller;

import Data.RowData;
import Data.TaskData;
import GUI.AddTask;
import GUI.DoneListView;

import GUI.ToDoListView;
import Model.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private ToDoListView toDoListView;
    private DoneListView doneListView;
    private AddTask addTask;
    private TaskData taskData;


    public Controller() {
        this.model = new Model();

        setToDoListViewVis();
    }

    public AbstractTableModel getModel() {
        return model.getModel();
    }

    public void setToDoListViewVis() {
        cleanView();
        toDoListView = new ToDoListView(this);
    }

    public void setDoneListViewVis() {
        cleanView();
        doneListView = new DoneListView(this);
    }

    public void setAddTaskViewVis() {
        cleanView();
        addTask = new AddTask(this);
    }

//    public ArrayList getArray() {
//        return model.getArray();
//    }

//    public Object[][] getRows(){
//        return model.getRows();
//    }

    private void cleanView() {

        if (toDoListView != null) {
            toDoListView.DisposeView();
        }
        if (doneListView != null) {
            doneListView.getFrame().dispose();
        }
        if (addTask != null) {
            addTask.getFrame().dispose();
        }
    }

    public void newTask(TaskData task,RowData row){
        model.newTask(task, row);
    }

}
