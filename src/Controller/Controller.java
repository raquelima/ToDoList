package Controller;

import Data.RowData;
import Data.TaskData;
import GUI.*;

import Model.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private ToDoListView toDoListView;
    private DoneListView doneListView;
    private AddTask addTask;
    private DetailView detailView;
    private EditTask editTask;


    public Controller() {
        this.model = new Model();
        setToDoListViewVis();
    }

    public AbstractTableModel getModel() {
        return model.getModel();
    }
    public AbstractTableModel getDoneModel() {
        return model.getDoneModel();
    }

    public void setToDoListViewVis() {
        cleanMainView();
        toDoListView = new ToDoListView(this);
    }

    public void setDoneListViewVis() {
        cleanMainView();
        doneListView = new DoneListView(this);
    }

    public void setAddTaskViewVis() {
        cleanSmallView();
        addTask = new AddTask(this);
    }

    public void setDetailViewVis(int index) {
        cleanSmallView();
        detailView = new DetailView(this, index);
    }

    public void setDetailViewVis(int index, int placeHolder) {
        cleanSmallView();
        detailView = new DetailView(this, index, placeHolder);
    }

    public void setEditTaskVis(int index) {
        cleanSmallView();
        editTask = new EditTask(this, index);
    }

    public void deleteTask(int length){
        model.deleteTask(length);
    }

    public void deleteDoneTask(int length){
        model.deleteDoneTask(length);
    }

    public void deleteAllTasks(){
        model.deleteAllTasks();
    }

    public ArrayList<RowData> getAllRows(){
        return model.getAllRows();
    }

    public ArrayList<RowData> getDoneTasksRow(){
        return model.getDoneTasksRow();
    }

    private void cleanMainView() {

        if (toDoListView != null) {
            toDoListView.DisposeView();
        }
        if (doneListView != null) {
            doneListView.DisposeView();
        }
        if (addTask != null) {
            addTask.DisposeView();
        }
        if (detailView != null) {
            detailView.DisposeView();
        }
        if (editTask != null) {
            editTask.DisposeView();
        }
    }

    private void cleanSmallView() {

        if (addTask != null) {
            addTask.DisposeView();
        }
        if (detailView != null) {
            detailView.DisposeView();
        }
        if (editTask != null) {
            editTask.DisposeView();
        }
    }

    public void newTask(TaskData task,RowData row){
        model.newTask(task, row);
    }

    public TaskData getTaskDetails(int index){
        return model.getTaskDetails(index);
    }

    public RowData getRowDetails(int index){
        return model.getRowDetails(index);
    }


}
