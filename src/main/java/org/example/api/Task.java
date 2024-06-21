package org.example.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="todo")
@NamedQuery(
        name = "org.example.api.Task.findAll",
        query = "SELECT t FROM Task t"
)
public class Task {

    enum Status {
        TODO, WIP, DONE
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status=Status.TODO;

    @Column(name="taskInfo", nullable = false)
    private String taskInfo;

    @Column(name = "startDate", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;

    @Column(name = "targetDate",nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date targetDate;

    public  Task(){

    }

    public Task(String taskInfo, Date startDate, Date targetDate){
        this.taskInfo=taskInfo;
        this.startDate=startDate;
        this.targetDate=targetDate;
    }

    public int getId(){
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public Status getStatus() {
        return status;
    }

    public String getTaskInfo() {
        return taskInfo;
    }
}
