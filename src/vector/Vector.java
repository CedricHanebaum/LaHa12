package vector;

public interface Vector {

	/**Addiert zwei Vektoren. Verändert dabei das this Objekt nicht.
	 * @param v2 der zu addierende Vektor.
	 * @return ein neuer Vektor, bestehend aus der Addition des this Objekt und v2.
	 */
	public Vector add(Vector v2);

	/**Multipliziert den Vektor mit einem Skalar.
	 * @param d Skalar, mit dcem der Vektor multipliziert werden soll.
	 * @return ein neuer Vektor, bestehend aus der multiplikation des this Objekts mit dem Skalar d.
	 */
	public Vector mult(double d);

	/**Berechnet das Skalarprodukt des this Objekts und dem in v2 uebergebenem Vektor.
	 * @param v2 Vektor, mit dem das Skalarprodukt berechnet werden soll.
	 * @return das Skalarprodukt von v2 und dem this Objekt.
	 */
	public double scalarProd(Vector v2);

	/**Berechnet die Standartnorm auf Basis des implementierten Skalarprodukts.
	 * @return die Standartnorm des this Objekts.
	 */
	public double getNorm();

	/**Berechnet den Winkel zwischen dem this Objekt und dem uebergebenem v2 Vektor.
	 * @param v2 Vektor zwischen dem und dem this Objekt der Winkel berechnet wird.
	 * @return der Winkel zwischen dem this Objekt und v2.
	 */
	public double getWinkel(Vector v2);

	/**Projeziert den this Vektor auf v2.
	 * @param v2 Vektor, auf den der this Vektor projeziert wird.
	 * @return die Projektion des this Vektors auf v2.
	 */
	public Vector projeziereV1AufV2(Vector v2);

}
