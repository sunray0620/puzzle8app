package model;

import java.util.ArrayList;

public class PriorityQueue {
	private ArrayList<PuzzleSolutionNode> list;
	public int size;

	public PriorityQueue() {
		list = new ArrayList<PuzzleSolutionNode>();
		size = 0;
	}

	public int size() {
		return size;
	}

	public void pushBack(PuzzleSolutionNode newNode) {
		if (size >= list.size()) {
			list.add(newNode);
		} else {
			list.set(size, newNode);
		}
		size += 1;
		siftUp(size - 1);
	}

	public PuzzleSolutionNode popFront() {
		if (size == 0) {
			return null;
		}
		PuzzleSolutionNode retNode = list.get(0);
		swapElement(0, size - 1);
		size -= 1;
		if (size > 0) {
			siftDown(0);
		}
		return retNode;
	}

	public void siftUp(int pos) {
		int parentPos = getParent(pos);
		while (parentPos >= 0 && list.get(pos).getHeuValue() < list.get(parentPos).getHeuValue()) {
			swapElement(pos, parentPos);
			pos = parentPos;
			parentPos = getParent(pos);
		}
	}

	public void siftDown(int pos) {
		int swapPosition = pos;
		int minValue = list.get(pos).getHeuValue();

		while (true) {
			int leftChild = getLeftChild(pos);
			if (leftChild < size && list.get(leftChild).getHeuValue() < minValue) {
				swapPosition = leftChild;
				minValue = list.get(leftChild).getHeuValue();
			}

			int rightChild = getRightChild(pos);
			if (rightChild < size && list.get(rightChild).getHeuValue() < minValue) {
				swapPosition = rightChild;
				minValue = list.get(rightChild).getHeuValue();
			}

			if (pos != swapPosition) {
				swapElement(pos, swapPosition);
				pos = swapPosition;
				minValue = list.get(pos).getHeuValue();
			} else {
				break;
			}
		}
	}

	private void swapElement(int p1, int p2) {
		PuzzleSolutionNode swapTemp = list.get(p1);
		list.set(p1, list.get(p2));
		list.set(p2, swapTemp);
	}

	private int getParent(int pos) {
		return (pos - 1) / 2;
	}

	private int getLeftChild(int pos) {
		return pos * 2 + 1;
	}

	private int getRightChild(int pos) {
		return pos * 2 + 2;
	}
}
