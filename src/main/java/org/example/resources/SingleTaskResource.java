package org.example.resources;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.api.Task;
import org.example.db.TaskDao;

import java.util.OptionalInt;

@Path("/todo/{taskId}")
@Produces(MediaType.APPLICATION_JSON)
public class SingleTaskResource {

    private final TaskDao taskDao;

    public  SingleTaskResource(TaskDao taskDao){
        this.taskDao=taskDao;
    }

    @GET
    @UnitOfWork
    public Task getTaskById(@PathParam("taskId") OptionalInt taskId) {
        return findSafely(taskId.orElseThrow(() -> new BadRequestException("task ID is required")));
    }

    @DELETE
    @UnitOfWork
    public String deleteTaskById(@PathParam("taskId") OptionalInt taskId) {
        Task task= findSafely(taskId.orElseThrow(() -> new BadRequestException("task ID is required")));
        taskDao.remove(task);
        return "Task deleted successfully";
    }

    private Task findSafely(int taskId) {
        return taskDao.findById(taskId).orElseThrow(() -> new NotFoundException("No such task found."));
    }
}
