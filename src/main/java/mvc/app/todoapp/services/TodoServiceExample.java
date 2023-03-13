package mvc.app.todoapp.services;

import mvc.app.todoapp.models.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoServiceExample {

    //Example with hardcoded values
    private static List<Todo> todos;
    private static int id = 0;

    static {
        todos = new ArrayList<>();
        todos.add(new Todo(++id, "Georgi", "Learn Java", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++id, "Georgi", "Get AWS Certified", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++id, "Georgi", "Learn React", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equals(username);
        return todos.stream().filter(predicate).toList(); // toList returns unmodifiableList
    }

    public void addTodo(String username, String description, LocalDate data, boolean isDone) {
        todos.add(new Todo(++id, username, description, data, isDone));
    }

    public void deleteTodoById(long id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(long id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
}
