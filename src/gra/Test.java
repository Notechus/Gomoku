package gra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Test extends JPanel
{
	private static final int COLUMNS = 19;
	private static final int ROWS = 19;
	private List<Rectangle> cells;
	private Color backgroundColor;

	public Test()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
		}
		cells = new ArrayList<>(COLUMNS * ROWS);
		initBoard();
		addMouseListener(mouseCallback);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// paintBoard(g);
		// g.fillRect(50, 50, 50, 50);

	}

	private void initBoard()
	{
		setLayout(new GridLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLUMNS; col++)
			{
				// gbc.gridx = col;
				// gbc.gridy = row;

				Cell cell = new Cell();
				Border border = null;
				if (row < ROWS)
				{
					if (col < COLUMNS)
					{
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else
					{
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else
				{
					if (col < COLUMNS)
					{
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} else
					{
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}
				cell.setBorder(border);
				add(cell);
			}
		}
	}

	public void paintBoard(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(backgroundColor);

		int width = getWidth();
		int height = getHeight();

		int cellWidth = width / COLUMNS - 5;
		int cellHeight = height / ROWS - 5;

		int xOffset = (width - (COLUMNS * cellWidth)) / 2;
		int yOffset = (height - (ROWS * cellHeight)) / 2;

		if (cells.isEmpty())
		{
			for (int row = 0; row < ROWS; row++)
			{
				for (int col = 0; col < COLUMNS; col++)
				{
					Rectangle cell = new Rectangle(xOffset + (col * cellWidth), yOffset + (row * cellHeight), cellWidth,
							cellHeight);
					cells.add(cell);
				}
			}
		}

		for (Rectangle cell : cells)
		{
			g2d.draw(cell);
		}
		g2d.dispose();
	}

	public void makeMove(Point p, int player)
	{

	}

	/**
	 * Handler eventów myszy
	 */
	private MouseListener mouseCallback = new MouseAdapter()
	{

		public void mousePressed(MouseEvent ev)
		{

		}

		public void mouseReleased(MouseEvent e)
		{

		}

		public void mouseEntered(MouseEvent e)
		{
			// setBackground(Color.BLUE);
		}

		public void mouseExited(MouseEvent e)
		{
			// setBackground(Color.WHITE);
			// repaint();
		}
	};

	/**
	 * Handler klawiatury
	 */
	private KeyListener keyCallback = new KeyAdapter()
	{
		public void keyReleased(KeyEvent ev)
		{
			switch (ev.getKeyCode())
			{
				case KeyEvent.VK_F:
					remove(1);
					break;
				case KeyEvent.VK_L:
					remove(-1);
					break;
				case KeyEvent.VK_BACK_SPACE:
					remove(0);
					break;
			};
			repaint();
		}
	};

}

class Cell extends JPanel
{
	private Color defaultBackground = Color.YELLOW;

	public Cell()
	{
		this.setBackground(defaultBackground);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				defaultBackground = getBackground();
				setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				setBackground(defaultBackground);
			}
		});
	}
}