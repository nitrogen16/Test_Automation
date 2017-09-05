package flowershop.flower;

public abstract class ManyFlowers extends Flowering {

	private int density;

	public ManyFlowers(String flowerName, int flowerSize, int flowerPrice, String color, int density) {
		super(flowerName, flowerSize, flowerPrice, color);
		this.setDensity(density);
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

}