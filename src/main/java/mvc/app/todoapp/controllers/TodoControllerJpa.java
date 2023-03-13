package mvc.app.todoapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mvc.app.todoapp.models.Todo;
import mvc.app.todoapp.services.TodoService;
import mvc.app.todoapp.services.TodoServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("name")
@RequiredArgsConstructor
public class TodoControllerJpa {

    private final TodoService todoService;

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoService.findByUsername(username);
        modelMap.addAttribute("todos", todos);
        return "listTodos";
    }

    // @RequestMapping handle GET and POST, so we should specify in the brackets
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap modelMap) {
        String username = getLoggedInUsername();
        // setting default values for new Todo
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoService.addTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam long id) {
        todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap modelMap) {
        Optional<Todo> todo = todoService.findById(id);
        modelMap.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
