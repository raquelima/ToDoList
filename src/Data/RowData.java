package Data;

import javax.swing.*;

public class RowData {
    private String title;
    private JCheckBox checkBox;
    private JButton delete;
    private JButton edit;
    private JButton details;

    public RowData(String title, JCheckBox checkBox, JButton details, JButton edit, JButton delete) {
        this.title = title;
        this.checkBox = checkBox;
        this.delete = delete;
        this.edit = edit;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public JButton getDelete() {
        return delete;
    }

    public void setDelete(JButton delete) {
        this.delete = delete;
    }

    public JButton getEdit() {
        return edit;
    }

    public void setEdit(JButton edit) {
        this.edit = edit;
    }

    public JButton getDetails() {
        return details;
    }

    public void setDetails(JButton details) {
        this.details = details;
    }

    public Object[] getAsArray(){
        return new Object[]{getTitle(),getCheckBox(),getDetails(),getEdit(),getDelete()};
    }

    public Object[] getAsArrayDone(){
        return new Object[]{getTitle(),getCheckBox(),getDetails(),getDelete()};
    }
}
