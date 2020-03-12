package edu.sda.data;


import java.util.Date;

public class Task {
    private String title;
    private Date dueDate;
    private String project;

    public Task (){
    }

    public Task (String title, Date dueDate, String project){
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getProject() {
        return project;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "Task: "
                + title +
                ", dueDate: " + dueDate +
                ", project:'" + project + '\'';
    }
}
