package mvc.app.todoapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(ModelMap modelMap) {
        modelMap.put("name", getLoggedInUsername());
        return "welcomePage";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

/*  Example:
  @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String welcomePage(@RequestParam String name, @RequestParam String password, ModelMap map) {
//
//        boolean authenticated = authenticationService.authenticate(name, password);
//        if (authenticated) {
//            map.put("name", name);
//            return "welcomePage";
//        } else {
//            map.put("errorMessage", "Invalid credentials. Please try again");
//            return "login";
//        }
   }*/

