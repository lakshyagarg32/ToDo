package org.example.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.api.Task;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TaskDao extends AbstractDAO<Task> {
    public  TaskDao(SessionFactory factory){
        super(factory);
    }

    public Task save(Task task){
        super.currentSession().persist(task);
        return task;
    }

    public Task update(Task task, Task newTask){
        super.currentSession().evict(task);
        super.currentSession().merge(newTask);
        return newTask;
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("org.example.api.Task.findAll"));
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public void remove(Task task) {
        super.currentSession().delete(task);
    }
}
