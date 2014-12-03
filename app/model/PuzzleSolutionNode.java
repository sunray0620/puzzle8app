package model;

public class PuzzleSolutionNode {
	public PuzzleBoard board;
	public int distanceToTarget;
	public String movePath;

	public int getHeuValue() {
		return movePath.length() + distanceToTarget;
	}
}
