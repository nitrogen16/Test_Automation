package flowershop.flower;

public abstract class OneFlower extends Flowering {

	private int petal;

	public OneFlower(String flowerName, int flowerSize, int flowerPrice, String color, int petal) {
		super(flowerName, flowerSize, flowerPrice, color);
		this.petal = petal;
	}

	public int getPetal() {

		return petal;
	}
}

// flowershop.flower.OneFlower Ð²Ñ�ÐµÐ³Ð´Ð° Ñ€Ð¾Ð¼Ð°ÑˆÐºÐ°, Ñ‚Ð¾ Ð² ÐºÐ¾Ð½Ñ�Ñ‚Ñ€
// Ð½Ðµ Ð½ÑƒÐ¶Ð½Ð¾ Ð¸Ð¼Ñ�