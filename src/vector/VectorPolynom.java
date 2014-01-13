package vector;

public class VectorPolynom implements Vector {

	
	public static final double ACC = 0.0001;
	private double[] koeffizienten;

	public VectorPolynom(double... koeffizienten) {
		this.koeffizienten = koeffizienten;
	}
	
	public VectorPolynom(VectorPolynom v2){
		this.koeffizienten = new double[v2.koeffizienten.length];
		for(int i = 0; i < v2.koeffizienten.length; i++){
			this.koeffizienten[i] = v2.koeffizienten[i];
		}
	}

	@Override
	public Vector add(Vector v2) {
		VectorPolynom vp;
		VectorPolynom ret;
		
		try {
			vp = (VectorPolynom) v2;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("An Error occured. It´s probably located between chair and keyboard.");
		}
		if (this.getGrad() > vp.getGrad()) {
			ret = new VectorPolynom(this);
			for (int i = 0; i < vp.koeffizienten.length; i++) {
				ret.koeffizienten[i] += vp.koeffizienten[i];
			}
		} else {
			ret = new VectorPolynom(vp);
			for (int i = 0; i < this.koeffizienten.length; i++) {
				ret.koeffizienten[i] += this.koeffizienten[i];
			}
		}
		
		return ret;
	}

	@Override
	public Vector mult(double d) {
		double k[] = koeffizienten;
		
		for (int i = 0; i < koeffizienten.length; i++) {
			k[i] *= d;
		}
		
		return new VectorPolynom(k);
	}

	@Override
	public double scalarProd(Vector v2) {
		try {
			return this.getMultIntegral(-1, 1, (VectorPolynom) v2);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("An Error occured. It´s probably located between chair and keyboard.");
		}
	}

	@Override
	public double getNorm() {
		return Math.sqrt(this.scalarProd(this));
	}

	@Override
	public double getWinkel(Vector v2) {
		return Math.acos(this.scalarProd(v2) / this.getNorm() * v2.getNorm());
	}

	@Override
	public Vector projeziereV1AufV2(Vector v2) {
		return v2.mult(this.scalarProd(v2) / v2.scalarProd(v2));
	}

	public int getGrad() {
		return koeffizienten.length - 1;
	}

	public double getMultIntegral(double x, double y, VectorPolynom v2) {
		double left = 0;
		double right = 0;
		
		for (double i = x; i < y; i += ACC) {
			left += ACC * (this.getValueAt(i) * v2.getValueAt(i));
		}
		for (double i = x + ACC; i <= y; i += ACC) {
			right += ACC * (this.getValueAt(i) * v2.getValueAt(i));
		}

		return (left + right) / 2;
	}

	public double getIntegral(double x, double y) {
		double left = 0;
		double right = 0;
		
		for (double i = x; i < y; i += ACC) {
			left += ACC * this.getValueAt(i);
		}
		for (double i = x + ACC; i <= y; i += ACC) {
			right += ACC * this.getValueAt(i);
		}

		return (left + right) / 2;
	}

	public double getValueAt(double x) {
		double ret = 0;
		
		for (int i = 0; i < koeffizienten.length; i++) {
			ret += koeffizienten[i] * Math.pow(x, i);
		}
		
		return ret;
	}

	public String toString() {
		String ret = "";
		
		for (int i = koeffizienten.length - 1; i >= 0; i--) {
			ret += " + " + koeffizienten[i] + "x^" + i;
		}
		
		return ret;
	}

}
