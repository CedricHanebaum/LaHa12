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
		VectorPolynom ret = new VectorPolynom(this);
		
		for (int i = 0; i < ret.koeffizienten.length; i++) {
			ret.koeffizienten[i] *= d;
		}
		
		return ret;
	}

	@Override
	public double scalarProd(Vector v2) {
		try {
//			return this.getMultIntegral(-1, 1, (VectorPolynom) v2);
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
	
	public VectorPolynom multPolynom(VectorPolynom v2){
		VectorPolynom ret = new VectorPolynom(new double[this.koeffizienten.length + v2.koeffizienten.length - 1]);
		
		for(int i = 0; i < this.koeffizienten.length; i++){
			for(int j = 0; j < v2.koeffizienten.length; j++){
				ret.koeffizienten[i + j] += this.koeffizienten[i] * v2.koeffizienten[j];
			}
		}
		
		return ret;
	}

	public int getGrad() {
		return koeffizienten.length - 1;
	}

	public VectorPolynom getIntegral() {
		VectorPolynom ret = new VectorPolynom(new double[this.koeffizienten.length + 1]);
		
		for(int i = 0; i < this.koeffizienten.length; i++){
			ret.koeffizienten[i + 1] = this.koeffizienten[i] /(i + 1);
		}
		
		return ret;
	}
	
	public double integrate(double x, double y){
		if(x >= y) throw new IllegalArgumentException("An Error occured. It´s probably located between chair and keyboard.");
		return this.getIntegral().getValueAt(y) - this.getIntegral().getValueAt(x);
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