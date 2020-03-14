package edu.sda.data;


import java.util.Date;

public class Task {
    private String title;
    private Date dueDate;
    private String project;
    private Status status;

    public Task (){
    }

    public Task (String title, Date dueDate, String project){
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.status = Status.TO_DO;
    }

    public Task(String title, Date dueDate, String project, Status status) {
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.status = status;
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

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Task: "
                + title +
                ", status: " + status +
                ", dueDate: " + dueDate +
                ", project:'" + project + '\'';
    }

}
