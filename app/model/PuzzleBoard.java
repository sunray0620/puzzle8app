package model;

public class PuzzleBoard {
	// Static member.
	public static final int boardHeight = 3;
	public static final int boardWidth = 3;
	public static final int numOfTiles = boardHeight * boardWidth;

	public static final long targetState = Long.parseLong("0000000100100011010001010110011110000000000000000000000000000000", 2);
	public static final int spaceSlot = 8;
	public static int[][] manhattanDist;

	static {
		// Initialize TaxiCab Distances
		manhattanDist = new int[numOfTiles][numOfTiles];
		for (int i = 0; i < numOfTiles; ++i) {
			for (int j = 0; j < numOfTiles; ++j) {
				int x1 = i / PuzzleBoard.boardWidth;
				int y1 = i % PuzzleBoard.boardWidth;
				int x2 = j / PuzzleBoard.boardWidth;
				int y2 = j % PuzzleBoard.boardWidth;
				manhattanDist[i][j] = Math.abs(x1 - x2) + Math.abs(y1 - y2);
			}
		}
	}

	// Data member.
	public long state;
	public int spacePos;

	/* Actions */

	public void initializeBoard(String str) {
		// Assert: str should be formatted as "0,1,2,3.."
		String[] nums = str.split(",");
		for (int i = 0; i < numOfTiles; ++i) {
			int parsedInt = Integer.parseInt(nums[i].trim());
			setValue(i, parsedInt);
			if (parsedInt == spaceSlot) {
				spacePos = i;
			}
		}
	}

	public boolean isDone() {
		return targetState == state;
	}

	public PuzzleBoard clone() {
		PuzzleBoard newBoard = new PuzzleBoard();
		newBoard.state = this.state;
		newBoard.spacePos = this.spacePos;
		return newBoard;
	}

	public int getTotalStateDistance() {
		int totalDistance = 0;
		for (int i = 0; i < numOfTiles; ++i) {
			int value = getValue(i);
			if (value != spaceSlot) {
				totalDistance += manhattanDist[value][i];
			}
		}
		return totalDistance;
	}

	/*
	 * Getter and Setter
	 */

	public int getValue(int i, int j) {
		int storagePos = getStoragePosition(i, j);
		return getValue(storagePos);
	}

	public int getValue(int pos) {
		int offset = (60 - pos * 4);
		long mask = (15L) << offset;
		return (int) ((mask & state) >>> offset);
	}

	public void setValue(int i, int j, int value) {
		int storagePos = getStoragePosition(i, j);
		setValue(storagePos, value);
	}

	public void setValue(int pos, int value) {
		int offset = (60 - pos * 4);
		long mask = ((state >> offset) & 15L ^ value);
		state = (mask << offset) ^ state;
	}

	private int getStoragePosition(int i, int j) {
		return i * boardWidth + j;
	}

	/*
	 * Moving Actions.
	 */

	public boolean moveLeftInPlace() {
		if (spacePos % PuzzleBoard.boardWidth == 0) {
			return false;
		}
		swap(spacePos, spacePos - 1);
		spacePos -= 1;
		return true;
	}

	public PuzzleBoard moveLeft() {
		PuzzleBoard newBoard = this.clone();
		if (newBoard.moveLeftInPlace()) {
			return newBoard;
		} else {
			return null;
		}
	}

	public boolean moveRightInPlace() {
		if (spacePos % PuzzleBoard.boardWidth == PuzzleBoard.boardWidth - 1) {
			return false;
		}
		swap(spacePos, spacePos + 1);
		spacePos += 1;
		return true;
	}

	public PuzzleBoard moveRight() {
		PuzzleBoard newBoard = this.clone();
		if (newBoard.moveRightInPlace()) {
			return newBoard;
		} else {
			return null;
		}
	}

	public boolean moveUpInPlace() {
		if (spacePos / PuzzleBoard.boardWidth == 0) {
			return false;
		}
		swap(spacePos, spacePos - PuzzleBoard.boardWidth);
		spacePos -= PuzzleBoard.boardWidth;
		return true;
	}

	public PuzzleBoard moveUp() {
		PuzzleBoard newBoard = this.clone();
		if (newBoard.moveUpInPlace()) {
			return newBoard;
		} else {
			return null;
		}
	}

	public boolean moveDownInPlace() {
		if (spacePos / PuzzleBoard.boardWidth == boardHeight - 1) {
			return false;
		}
		swap(spacePos, spacePos + PuzzleBoard.boardWidth);
		spacePos += PuzzleBoard.boardWidth;
		return true;
	}

	public PuzzleBoard moveDown() {
		PuzzleBoard newBoard = this.clone();
		if (newBoard.moveDownInPlace()) {
			return newBoard;
		} else {
			return null;
		}
	}

	private void swap(int pos1, int pos2) {
		int swapTemp = getValue(pos1);
		setValue(pos1, getValue(pos2));
		setValue(pos2, swapTemp);
	}

	/*
	 * Printing and debugging.
	 */

	public void printBinaryRepresentation() {
		String str = Long.toBinaryString(state);
		String strWithLeadingZeros = String.format("%64s", str).replace(' ', '0');
		System.out.println(strWithLeadingZeros);
	}

	public void printBoard() {
		for (int i = 0; i < boardHeight; ++i) {
			for (int j = 0; j < boardWidth; ++j) {
				int cellValue = getValue(i, j);
				String charStr = String.format("%5d\t", cellValue);
				System.out.print(charStr);
			}
			System.out.println();
		}
		System.out.println();
		if (getValue(spacePos) != spaceSlot) {
			System.out.println("Space position is not right");
			System.exit(-1);
		}
	}
}
