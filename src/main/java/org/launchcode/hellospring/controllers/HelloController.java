package org.launchcode.hellospring.controllers;

/* To designate a given class as a controller within the Spring framework,
we use the annotation @Controller. Recall that Java Annotations are like metadata about your code.
They help the framework do its work by adding context to your code.
*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class HelloController {

    //Handles request at path/hello or localhost:8080
    //@GetMapping("hello")
    //the url: http://localhost:8080/hello
    //@ResponseBody
    //public String hello(){
    // return "Hello, Summer! 'It is 93000 damn degrees!'";
    //}

    //the url: http://localhost:8080/goodbye
    @GetMapping("goodbye")

    public String goodbye() {
        return "Later gator!";
    }

    /*Handles requests of the form /hello?name=LaunchCode this is a dynamic handler in other words, it accepts
    data a string called name @RequestParam handles that
    the name in the hello?name=LaunchCode needs to match in the @RequestParam String name it won't work
     the url: http://localhost:8080/hello?name=LaunchCode  can change =LaunchCode to any name*/
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="hello")

    public String helloWithQueryParam(@RequestParam String name, @RequestParam String language) {
        String properGreeting = HelloController.createMessage(name, language);
        return "<h3 style= 'color: grey;'>" + properGreeting + "</h3>";
    }

    // Handles requests of the form /hello/LaunchCode the difference in our requests now the
    // variable is apart of the path, not the query string
    //{name} is called a path param needs to be the same as the name in the method param list
    //@PathVariable takes an argument that, if matching a portion of the URL,
    // will deliver this data into the handler.
    //the url: http://localhost:8080/hello/LaunchCode can change the /LaunchCode to any name
    @GetMapping("hello/{name}")
    public String helloWithPathParam(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("form")
    public String helloForm() {

        /*
        <select name = "pets" id = "pet-select">
        <option value = "">--Please choose an option--</option>
        <option value = "dog">Dog</option>
        <option value = "cat">Cat</option>
        <option value = "hamster">Hamster</option>
        <option value = "parrot">Parrot</option>
        <option value = "spider">Spider</option>
        <option value = "goldfish">Goldfish</option>
        </select>
         */
        return "<html>" +
                "<body>" +
                "<form action = 'hello' method='post'>" + //submit request ot /hello
                "<input type = 'text' name = 'name' />" +
                "<select name='language'>" +
                    "<option value='English'>English</option>" +
                    "<option value='Spanish'>Spanish</option>" +
                    "<option value='Italian'>Italian</option>" +
                    "<option value='Japanese'>Japanese</option>" +
                    "<option value='Patois'>Patois</option>" +
                "</select>" +
                "<input type = 'submit' value = 'Greet Me!' />" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    public static String createMessage(String name, String language) {
        switch(language) {
            case "Spanish":
                return "Hola, " + name;
            case "Italian":
                return "Ciao, " + name;
            case "Japanese":
                return "Konnichiwa, " + name;
            case "Patois":
                return "Wah Gwaan, " + name;
            case "English":
            default:
                return "Hello, " + name;
        }
    }
}

