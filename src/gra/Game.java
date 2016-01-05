package gra;

import java.awt.Color;
import java.awt.Point;

public class Game
{
	private boolean playerWin;
	private boolean computerWin;
	private boolean draw;
	private boolean end = false;
	private ComputerAI computer;

	private int moves = 19 * 19;

	private int board[][];

	public Game()
	{
		playerWin = false;
		computerWin = false;
		draw = false;
		board = new int[19][19];
		for (int i = 0; i < 19; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				board[i][j] = 0;
			}
		}
	}

	public int[][] getBoard()
	{
		return board;
	}

	public boolean isPlayerWin()
	{
		return playerWin;
	}

	public void setPlayerWin(boolean playerWin)
	{
		this.playerWin = playerWin;
	}

	public boolean isComputerWin()
	{
		return computerWin;
	}

	public void setComputerWin(boolean computerWin)
	{
		this.computerWin = computerWin;
	}

	public boolean isDraw()
	{
		return draw;
	}

	public void setDraw(boolean draw)
	{
		this.draw = draw;
	}

	public boolean isEnd()
	{
		return end;
	}

	public void setEnd(boolean end)
	{
		this.end = end;
	}

	public int getMoves()
	{
		return moves;
	}

	public void setMoves(int moves)
	{
		this.moves = moves;
	}

	public void makeMove(int x, int y, int player)
	{
		// -1 will be computer, 1 will be player
		if (moves != 0 && board[x][y] == 0) board[x][y] = player;
		moves--;
		if (moves == 0) setDraw();
	}

	private void setDraw()
	{
		draw = true;
		computerWin = playerWin = false;
		end = true;
	}

	private void setPlayerWin()
	{
		playerWin = true;
		computerWin = draw = false;
		end = true;
	}

	private void setComputerWin()
	{
		computerWin = true;
		playerWin = draw = false;
		end = true;
	}

	public Line checkPlayerWin()
	{
		Point pocz = null, kon = null;
		Line l = null;
		int tmp = 0;
		boolean found = false;
		String txt = "Wygrałeś :)";
		// find pattern in rows
		for (int i = 0; i < 19; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				if (board[i][j] == 1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = j + 1; a < j + 5; a++)
					{
						if (board[i][a] == 1)
						{
							tmp++;
							if (tmp == 5)
							{
								kon = new Point(i, a);
								found = true;
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}
		// find pattern in columns
		tmp = 0;
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				if (board[i][j] == 1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = i + 1; a < i + 5; a++)
					{
						if (board[a][j] == 1)
						{
							tmp++;
							if (tmp == 5)
							{
								kon = new Point(a, j);
								found = true;
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}
		// find pattern in slash(/)
		tmp = 0;
		for (int i = 4; i < 19; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				if (board[i][j] == 1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = 1; a < 5; a++)
					{
						if (board[i - a][j + a] == 1)
						{
							tmp++;
							if (tmp == 5)
							{
								found = true;
								kon = new Point(i - a, j + a);
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}

		// find pattern in backslash(\)
		tmp = 0;
		for (int i = 18; i > 0; i--)
		{
			for (int j = 4; j < 19; j++)
			{
				if (board[i][j] == 1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = 1; a < 5; a++)
					{
						if (board[i - a][j - a] == 1)
						{
							tmp++;
							if (tmp == 5)
							{
								kon = new Point(i - a, j - a);
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
								found = true;
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}
		return l;
	}

	public Line checkComputerWin()
	{
		Point pocz = null, kon = null;
		Line l = null;
		int tmp = 0;
		boolean found = false;
		String txt = "Komputer wygrał :(";
		// find pattern in rows
		for (int i = 0; i < 19; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				if (board[i][j] == -1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = j + 1; a < j + 5; a++)
					{
						if (board[i][a] == -1)
						{
							tmp++;
							if (tmp == 5)
							{
								kon = new Point(i, a);
								found = true;
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}
		// find pattern in columns
		tmp = 0;
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				if (board[i][j] == -1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = i + 1; a < i + 5; a++)
					{
						if (board[a][j] == -1)
						{
							tmp++;
							if (tmp == 5)
							{
								kon = new Point(a, j);
								found = true;
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}
		// find pattern in slash(/)
		tmp = 0;
		for (int i = 4; i < 19; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				if (board[i][j] == -1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = 1; a < 5; a++)
					{
						if (board[i - a][j + a] == -1)
						{
							tmp++;
							if (tmp == 5)
							{
								found = true;
								kon = new Point(i - a, j + a);
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}

		// find pattern in backslash(\)
		tmp = 0;
		for (int i = 18; i > 0; i--)
		{
			for (int j = 4; j < 19; j++)
			{
				if (board[i][j] == -1)
				{
					tmp++;
					pocz = new Point(i, j);
					for (int a = 1; a < 5; a++)
					{
						if (board[i - a][j - a] == -1)
						{
							tmp++;
							if (tmp == 5)
							{
								kon = new Point(i - a, j - a);
								if (l != null) l = new Line(pocz, kon, Color.BLACK);
								found = true;
							}
							continue;
						} else
						{
							found = false;
							tmp = 0;
							break;
						}
					}
					if (found)
					{
						setPlayerWin();
						GameWindow.updateResult(txt);
						break;
					}
				}
			}
		}
		return l;
	}

	public void checkDraw()
	{
		if (draw) GameWindow.updateResult("Remis");
	}

}
