package gra;

import java.awt.Point;

public class ComputerAI
{
	private Point begin_max_path;
	private Point end_max_path;
	public boolean myTurn;

	public ComputerAI(Point max, boolean turn)
	{
		begin_max_path = max;
		myTurn = turn;
	}

	public Point makeMove(int[][] board)
	{
		Point p = null;
		// find where opponent has 3 or more and block it
		// otherwise add somewhere where u have max path
		// should use Minimax algorithm
		return p;
	}
}
