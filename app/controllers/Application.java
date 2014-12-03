package controllers;

//import play.*;
import model.PuzzleBoard;
import model.PuzzleSolution;
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
		PuzzleBoard initBoard = new PuzzleBoard();
		initBoard.initializeBoard(body.asText());
		PuzzleSolution puzzleSln = new PuzzleSolution(initBoard);
		String sln = puzzleSln.solvePuzzle();

		return ok(sln);
	}

	public static Result solve() {
		return ok("fsf");
	}

}
