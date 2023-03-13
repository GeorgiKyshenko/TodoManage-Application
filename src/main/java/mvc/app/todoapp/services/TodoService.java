package mvc.app.todoapp.services;

import mvc.app.todoapp.models.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    void addTodo(Todo todo);

    List<Todo> findByUsername(String username);
    void deleteTodoById(long id);
    Optional<Todo> findById(long id);
    void updateTodo(Todo todo);
}
