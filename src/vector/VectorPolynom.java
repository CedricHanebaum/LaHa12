package vector;

public class VectorPolynom implements Vector {
	
	private double[] koeffizienten;
	
	public VectorPolynom(double... koeffizienten){
		this.koeffizienten = koeffizienten;
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
		if(this.gerGrad() > vp.gerGrad()) {
			ret = new VectorPolynom(koeffizienten);
			for(int i = 0; i < vp.koeffizienten.length; i++){
				ret.koeffizienten[i] += vp.koeffizienten[i];
			}
		} else {
			ret = new VectorPolynom(vp.koeffizienten);
			for(int i = 0; i < this.koeffizienten.length; i++){
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNorm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWinkel(Vector v2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector projeziereV1AufV2(Vector v2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int gerGrad(){
		return koeffizienten.length - 1;
	}

}
