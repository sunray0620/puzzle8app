package controllers;

//import play.*;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import views.html.index;
import views.html.puzzle;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Home page"));
	}

	public static Result puzzle() {
		return ok(puzzle.render(""));
	}

	public static Result validate() {
		RequestBody body = request().body();

		return ok("Got body: " + body.asText());
	}

	public static Result solve() {
		return ok("fsf");
	}

}
