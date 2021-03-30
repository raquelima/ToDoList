package Data;

public class TaskData {
    private String title;
    private String dueData;
    private int priority;
    private String description;

    public TaskData(String title, String dueData, int priority, String description) {
        this.title = title;
        this.dueData = dueData;
        this.priority = priority;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueData() {
        return dueData;
    }

    public void setDueData(String dueData) {
        this.dueData = dueData;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
