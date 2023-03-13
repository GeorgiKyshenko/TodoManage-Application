package mvc.app.todoapp.services.impl;

import lombok.RequiredArgsConstructor;
import mvc.app.todoapp.models.Todo;
import mvc.app.todoapp.repositories.TodoRepository;
import mvc.app.todoapp.services.TodoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    @Override
    public void deleteTodoById(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Optional<Todo> findById(long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        if (todo.getTargetDate().equals(LocalDate.now())) {
            todo.setDone(true);
        }
        todoRepository.save(todo);
    }
}
