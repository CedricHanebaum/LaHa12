package vector;

import java.util.ArrayList;

/**
 * Speichert einen n Dimensionalen Vektor und stellt grundlegende
 * Rechenoperationen zur Verfügung.
 * @author chanebaum
 */

public class VektorRn implements Vector {

	/** Double Array, speichert die Elemente des Vektors */
	private double[] vektor;

	/**
	 * Erzeugt ein VektorRn Objekt mit beliebig vielen Elementen.
	 * @param pVektor Elemente des Vektors
	 */
	public VektorRn(double... pVektor) {
		vektor = pVektor;
	}

	public double[] getElements() {
		return vektor;
	}

	/**
	 * Addiert den uebergebenen Vektor zum this Vektor und gibt den
	 * Ergebnisvektor zurueck.
	 * @param v2 addiert Vektor v2 zum this Vektor
	 * @return v2 + this
	 * @throws RuntimeException falls Dimensionen nicht konsistent
	 */
	public VektorRn add(Vector v2) {
		VektorRn vr;
		try {
			vr = (VektorRn) v2;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("An Error occured. It´s probably located between chair and keyboard.");
		}
		if (vektor.length != vr.vektor.length) throw new RuntimeException();

		double v[] = new double[vektor.length];
		for (int i = 0; i < v.length; i++) {
			v[i] = vektor[i] + vr.vektor[i];
		}

		return new VektorRn(v);
	}

	/**
	 * bestimmt alle Senkrecht zum this Vektor stehenden Vektoren.
	 * @return Array mit zum this Vektor senkrecht stehenden Vektoren.
	 */
	public VektorRn[] bestimmeSenkrechteVektoren() {
		VektorRn[] ans = new VektorRn[vektor.length - 1];
		ArrayList<Integer> al = new ArrayList<Integer>();
		int i = -1;

		try {
			for (; ++i < vektor.length & vektor[i] == 0;)
				;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException();
		}

		for (int k = 0; k < vektor.length - 1; k++) {
			int j = -1;

			for (; ++j < vektor.length & (j == i | al.contains(j));)
				;
			al.add(j);

			ans[k] = new VektorRn();
			ans[k].vektor = new double[vektor.length];
			ans[k].vektor[i] = vektor[j];
			ans[k].vektor[j] = -1 * vektor[i];
			for (int l = 0; l < vektor.length; l++) {
				if (l != i && l != j) {
					ans[k].vektor[l] = 0;
				}
			}
		}
		return ans;
	}

	/**
	 * Multipliziert den this Vektor mit einem Skalar und gibt den
	 * Ergebnisvektor zurueck.
	 * @param d Skalar, wird mit this Vektor multipiziert
	 * @return Produkt aus this und d
	 */
	public VektorRn mult(double d) {
		double v[] = vektor;
		for (int i = 0; i < vektor.length; i++) {
			v[i] *= d;
		}
		return new VektorRn(v);
	}

	/**
	 * Prueft, ob der uebergebene Vektor parallel zum this Vektor ist.
	 * @param v2 Vektor, wird auf parallelität zu this Vektor geprüft
	 * @return v2 parallel zu this Vektor
	 * @throws RuntimeException falls Dimensionen nicht konsistent
	 */
	public boolean isParallel(VektorRn v2) {
		if (vektor.length != v2.vektor.length) throw new RuntimeException();
		double a = vektor[0] / v2.vektor[0];
		for (int i = 1; i < vektor.length; i++) {
			if ((vektor[i] / v2.vektor[i]) != a) return false;
		}
		return true;
	}

	public double getNorm() {
		double tmp = 0;
		for (double d : vektor) {
			tmp += Math.pow(d, 2);
		}
		return Math.sqrt(tmp);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + vektor[0]);
		for (int i = 1; i < vektor.length; i++) {
			sb.append(", " + vektor[i]);
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public double scalarProd(Vector v2) {
		double ret = 0;
		VektorRn vr;
		try {
			vr = (VektorRn) v2;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("An Error occured. It´s probably located between chair and keyboard.");
		}
		if (vektor.length != vr.vektor.length) throw new RuntimeException();
		for (int i = 0; i < this.vektor.length; i++)
			ret += (this.vektor[i] * vr.vektor[i]);
		return ret;
	}

	@Override
	public double getWinkel(Vector v2) {
		return Math.acos(this.scalarProd(v2) / this.getNorm() * v2.getNorm());
	}

	@Override
	public Vector projeziereV1AufV2(Vector v2) {
		return v2.mult(this.scalarProd(v2) / v2.scalarProd(v2));
	}

}
