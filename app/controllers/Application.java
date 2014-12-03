package controllers;

//import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Home page"));
    }
    
    public static Result puzzle() {
        return ok(puzzle.render(""));
    }
    
    public static Result validate() {
        return ok("fsf");
    }
    
    public static Result solve() {
        return ok("fsf");
    }

}
