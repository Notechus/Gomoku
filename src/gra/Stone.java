package gra;

import java.awt.Color;
import java.awt.Point;

public class Stone implements Drawable
{
	public Point center;
	public int radius;
	public Color color;

	public Stone(Point cent, int r, Color c)
	{
		center = cent;
		radius = r;
		color = c;
	}

	@Override
	public void draw()
	{

	}

	@Override
	public void remove()
	{

	}
}
