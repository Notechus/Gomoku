package gra;

import java.awt.Color;
import java.awt.Point;

/**
 * Klasa implementuj�ca odcinek na p�aszczy�nie
 * 
 * @author Sebastian
 */
public class Line implements Drawable
{

	/** Pocz�tek i koniec odcinka */
	protected Point poczatek, koniec;
	/** Kolor odcinka */
	public final Color kolor;

	/**
	 * Konstruuje odcinek z podanymi parametrami
	 * 
	 * @param pocz poczatkowy punkt odcinka
	 * @param kon ko�cowy punkt odcinka
	 * @param kol kolor
	 */
	public Line(Point pocz, Point kon, Color kol)
	{
		poczatek = pocz;
		koniec = kon;
		kolor = kol;
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
