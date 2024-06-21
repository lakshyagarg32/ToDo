package org.example.resources;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.api.Task;
import org.example.db.TaskDao;

import java.util.List;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDao taskDao;

    public  TaskResource(TaskDao taskDao){
        this.taskDao=taskDao;
    }

    @POST
    @UnitOfWork
    public Task createTask(@Valid Task task) {
        return taskDao.save(task);
    }

    @PUT
    @UnitOfWork
    public Task updateTaskById(@Valid Task newTask) {
        Task task= findSafely(newTask.getId());
        return taskDao.update(task,newTask);
    }

    @GET
    @UnitOfWork
    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }

    private Task findSafely(int taskId) {
        return taskDao.findById(taskId).orElseThrow(() -> new NotFoundException("No such task found."));
    }

}
