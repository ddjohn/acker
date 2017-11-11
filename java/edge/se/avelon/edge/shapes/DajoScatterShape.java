package se.avelon.edge.shapes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import se.avelon.edge.datasets.scatter.DajoScatterConstants;

public class DajoScatterShape implements Shape {
	
	private Ellipse2D circle = null;
	private Ellipse2D high = null;
	private Rectangle2D low = null;

	public DajoScatterShape(int states) {
		int radios = 1;

		if((states & DajoScatterConstants.SINGEL_RICOCHETTE) == DajoScatterConstants.SINGEL_RICOCHETTE) {
			radios = 3;
		}

		if((states & DajoScatterConstants.DOUBLE_RICOCHETTE) == DajoScatterConstants.DOUBLE_RICOCHETTE) {
			radios = 5;
		}


		if((states & DajoScatterConstants.DONHIGH) == DajoScatterConstants.DONHIGH) {
			System.out.println("DonchianHigh");
			high = new Ellipse2D.Double(0, 0, 10, 5);
		}

		if((states & DajoScatterConstants.DONLOW) == DajoScatterConstants.DONLOW) {
			System.out.println("DonchianLow");
			low = new Rectangle2D.Double(0, 0, 5, 10);
		}

		circle =  new Ellipse2D.Double(0, 0, radios, radios);

	}

	public void draw(Graphics g) {
		g.drawRoundRect((int)circle.getCenterX(), (int)circle.getCenterY(), (int)circle.getWidth(), (int)circle.getHeight(), 1, 1);
		System.out.println("->Draw");

		if(high != null) {
			System.out.println("->Highle");

			g.drawRoundRect((int)high.getCenterX(), (int)high.getCenterY(), (int)high.getWidth(), (int)high.getHeight(), 1, 1);
		}

		if(low != null) {
			g.drawRect((int)low.getCenterX(), (int)low.getCenterY(), (int)low.getWidth(), (int)low.getHeight());			
		}
	}

	public void fill(Graphics g) {
		g.fillRoundRect((int)circle.getCenterX(), (int)circle.getCenterY(), (int)circle.getWidth(), (int)circle.getHeight(), 1, 1);
		System.out.println("->Fill");

		if(high != null) {
			System.out.println("->Low");
			g.fillRect((int)high.getCenterX(), (int)high.getCenterY(), (int)high.getWidth(), (int)high.getHeight());
		}

		if(low != null) {
			g.fillRect((int)low.getCenterX(), (int)low.getCenterY(), (int)low.getWidth(), (int)low.getHeight());			
		}
	}

	public Rectangle getBounds() {
		//return circle.getBounds();
		return new Rectangle(-20, -20, 20, 20);
	}

	public Rectangle2D getBounds2D() {
		//return circle.getBounds2D();
		return new Rectangle(-20, -20, 20, 20);
	}

	public boolean contains(double x, double y) {
		//return circle.contains(x, y);

		if(circle.contains(x, y) == true) {
			return true;
		}

		if(low != null && low.contains(x, y) == true) {
			return true;
		}

		if(high != null && high.contains(x, y) == true) {
			return true;
		}

		return false;
	}			

	public boolean contains(Point2D p) {
		//return circle.contains(p);

		if(              circle.contains(p) == true) {return true;}
		if(low  != null &&  low.contains(p) == true) {return true;}
		if(high != null && high.contains(p) == true) {return true;}

		return false;
	}

	public boolean intersects(double x, double y, double w, double h) {
		//		return circle.intersects(x, y, w, h);

		if(              circle.intersects(x, y, w, h) == true) {return true;}
		if(low  != null &&  low.intersects(x, y, w, h) == true) {return true;}
		if(high != null && high.intersects(x, y, w, h) == true) {return true;}

		return false;
	}

	public boolean intersects(Rectangle2D r) {
		//		return circle.intersects(r);
		if(              circle.intersects(r) == true) {return true;}
		if(low  != null &&  low.intersects(r) == true) {return true;}
		if(high != null && high.intersects(r) == true) {return true;}

		return false;
	}

	public boolean contains(double x, double y, double w, double h) {
		//		return circle.contains(x, y, w, h);

		if(              circle.contains(x, y) == true) {return true;}
		if(low  != null &&  low.contains(x, y) == true) {return true;}
		if(high != null && high.contains(x, y) == true) {return true;}

		return false;
	}

	public boolean contains(Rectangle2D r) {
		//		return circle.intersects(r);

		if(              circle.contains(r) == true) {return true;}
		if(low  != null &&  low.contains(r) == true) {return true;}
		if(high != null && high.contains(r) == true) {return true;}

		return false;
	}

	public PathIterator getPathIterator(AffineTransform at) {
		return circle.getPathIterator(at);
	}

	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return circle.getPathIterator(at, flatness);
	}
}