package vector;

/**
 * Stellt einen Vektor aus dem Vektorraum der Polynome dar und stellt
 * verschiedene Opperationen zur Verfügung?
 * @author chanebaum
 */

public class VectorPolynom implements Vector {

	private double[] koeffizienten;

	/**Erzeugt ein neues Objekt vom Typ VectorPolynom mit den uebergebenen Parametern als Koeffizienten.
	 * @param koeffizienten die Koeffizienten des Polynoms.
	 */
	public VectorPolynom(double... koeffizienten) {
		this.koeffizienten = koeffizienten;
	}

	/**Erezugt ein neues Objekt vom Typ Polynom als Kopie des uebergebenen Objekts.
	 * @param v2 zu kopierendes Polynom.
	 */
	public VectorPolynom(VectorPolynom v2) {
		this.koeffizienten = new double[v2.koeffizienten.length];
		for (int i = 0; i < v2.koeffizienten.length; i++) {
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
		if (this.koeffizienten.length > vp.koeffizienten.length) {
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
		VectorPolynom ret = new VectorPolynom(this);

		for (int i = 0; i < ret.koeffizienten.length; i++) {
			ret.koeffizienten[i] *= d;
		}

		return ret;
	}

	@Override
	public double scalarProd(Vector v2) {
		try {
			VectorPolynom v = this.multPolynom((VectorPolynom) v2);
			return v.integrate(-1, 1);
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

	/**
	 * multipliziert das this Objekt mit dem als v2 uebergebenem Polynom.
	 * @param v2 Polynom, das mit dem this Objekt multipliziert werden soll.
	 * @return Polynom, bestehend aus dem Produkt des this Objekts und des
	 *         Polynoms v2.
	 */
	public VectorPolynom multPolynom(VectorPolynom v2) {
		VectorPolynom ret = new VectorPolynom(new double[this.koeffizienten.length + v2.koeffizienten.length - 1]);

		for (int i = 0; i < this.koeffizienten.length; i++) {
			for (int j = 0; j < v2.koeffizienten.length; j++) {
				ret.koeffizienten[i + j] += this.koeffizienten[i] * v2.koeffizienten[j];
			}
		}

		return ret;
	}

	/**
	 * Berechnet das Integral des Polynoms.
	 * @return ein neues Polynome, Integral des this Objekts.
	 */
	public VectorPolynom getIntegral() {
		VectorPolynom ret = new VectorPolynom(new double[this.koeffizienten.length + 1]);

		for (int i = 0; i < this.koeffizienten.length; i++) {
			ret.koeffizienten[i + 1] = this.koeffizienten[i] / (i + 1);
		}

		return ret;
	}

	/**
	 * Berechnet das Integral in den Grenzen x bis y.
	 * @param x berechnet das Integral ab diesem Wert.
	 * @param y berechnet das Integral bis zu diesem Wert.
	 * @return Der Wert des Integrals in den Grenzen x bis y.
	 */
	public double integrate(double x, double y) {
		if (x >= y) throw new IllegalArgumentException("An Error occured. It´s probably located between chair and keyboard.");
		return this.getIntegral().getValueAt(y) - this.getIntegral().getValueAt(x);
	}

	/**
	 * Berechnet den Funktionswert an der Stelle x.
	 * @param x Position des gesuchten Funktionswerts.
	 * @return der Funktionswert an der Stelle x.
	 */
	public double getValueAt(double x) {
		double ret = 0;

		for (int i = 0; i < koeffizienten.length; i++) {
			ret += koeffizienten[i] * Math.pow(x, i);
		}

		return ret;
	}

	@Override
	public String toString() {
		String ret = "";

		for (int i = koeffizienten.length - 1; i >= 0; i--) {
			ret += " + " + koeffizienten[i] + "x^" + i;
		}

		return ret;
	}

}