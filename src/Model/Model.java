package Model;

import Data.RowData;
import Data.TaskData;

import java.util.ArrayList;

public class Model {

    private ArrayList<TaskData> tasks= new ArrayList<>();
    private ArrayList<RowData> rows= new ArrayList<>();

    public void newTask(TaskData task, RowData row){
        tasks.add(task);
        rows.add(row);
    }

    public ArrayList getArray(){
        return tasks;
    }

    public Object[][] getRows(){
        Object[][] bro = {rows.toArray()};
        return bro;
    }

    public Model() {

    }
}
