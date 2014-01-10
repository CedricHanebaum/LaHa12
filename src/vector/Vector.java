package vector;

public interface Vector {

	public Vector add(Vector v2);

	public Vector mult(double d);

	public double scalarProd(Vector v2);

	public double getNorm();

	public double getWinkel(Vector v2);

	public Vector projeziereV1AufV2(Vector v2);

}
