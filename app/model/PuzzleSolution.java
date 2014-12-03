package model;

import java.util.HashSet;

public class PuzzleSolution {
	// Initial state;
	private PuzzleBoard initialState;
	// Priority Queue
	private PriorityQueue pqueue;
	// Hashmap to save visit info.
	private HashSet<Long> seen;

	public PuzzleSolution(PuzzleBoard initBoard) {
		this.initialState = initBoard;
		this.pqueue = new PriorityQueue();
		this.seen = new HashSet<Long>();
	}

	public String solvePuzzle() {
		PuzzleSolutionNode initialNode = new PuzzleSolutionNode();
		initialNode.board = initialState;
		initialNode.distanceToTarget = initialState.getTotalStateDistance();
		initialNode.movePath = "";
		pqueue.pushBack(initialNode);
		seen.add(initialNode.board.state);

		while (pqueue.size() > 0) {
			// Dequeue one node;
			PuzzleSolutionNode curNode = pqueue.popFront();

			// System.out.println(String.format("Step #%d,\t In queue: %d;\t Seen: %d", numOfIters, pqueue.size(), seen.size()));
			// System.out.println(String.format("dist %d, heur %d", curNode.movePath.length(), curNode.getHeuValue()));
			// curNode.board.printBoard();

			// Check completion status;
			if (curNode.board.isDone()) {
				System.out.println(String.format("I found an optimal solution with %d steps", curNode.movePath.length()));
				System.out.println(String.format("move: %s", curNode.movePath));
				return curNode.movePath;
			}

			// Visit all its neighbors, by exploring possible moves.
			PuzzleBoard moveUpState = curNode.board.moveUp();
			if (moveUpState != null && !seen.contains(moveUpState.state)) {
				seen.add(moveUpState.state);
				PuzzleSolutionNode newSolutionNode = new PuzzleSolutionNode();
				newSolutionNode.board = moveUpState;
				newSolutionNode.distanceToTarget = moveUpState.getTotalStateDistance();
				newSolutionNode.movePath = curNode.movePath + '1';
				pqueue.pushBack(newSolutionNode);
			}

			PuzzleBoard moveDownState = curNode.board.moveDown();
			if (moveDownState != null && !seen.contains(moveDownState.state)) {
				seen.add(moveDownState.state);
				PuzzleSolutionNode newSolutionNode = new PuzzleSolutionNode();
				newSolutionNode.board = moveDownState;
				newSolutionNode.distanceToTarget = moveDownState.getTotalStateDistance();
				newSolutionNode.movePath = curNode.movePath + '2';
				pqueue.pushBack(newSolutionNode);
			}

			PuzzleBoard moveLeftState = curNode.board.moveLeft();
			if (moveLeftState != null && !seen.contains(moveLeftState.state)) {
				seen.add(moveLeftState.state);
				PuzzleSolutionNode newSolutionNode = new PuzzleSolutionNode();
				newSolutionNode.board = moveLeftState;
				newSolutionNode.distanceToTarget = moveLeftState.getTotalStateDistance();
				newSolutionNode.movePath = curNode.movePath + '3';
				pqueue.pushBack(newSolutionNode);
			}

			PuzzleBoard moveRightState = curNode.board.moveRight();
			if (moveRightState != null && !seen.contains(moveRightState.state)) {
				seen.add(moveRightState.state);
				PuzzleSolutionNode newSolutionNode = new PuzzleSolutionNode();
				newSolutionNode.board = moveRightState;
				newSolutionNode.distanceToTarget = moveRightState.getTotalStateDistance();
				newSolutionNode.movePath = curNode.movePath + '4';
				pqueue.pushBack(newSolutionNode);
			}
		}
		return "NULL";
	}

}