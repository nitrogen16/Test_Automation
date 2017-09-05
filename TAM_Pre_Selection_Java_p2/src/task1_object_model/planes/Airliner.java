package task1_object_model.planes;

public class Airliner extends Plane {

	private int airlinerCapacity;

	public int getAirlinerCapacity() {
		return airlinerCapacity;
	}

	public void setAirlinerCapacity(int airlinerCapacity) {
		this.airlinerCapacity = airlinerCapacity;
	}

	public Airliner(String planeName, int freightAircraftFlyingRange) {
		super(planeName, freightAircraftFlyingRange);
	}

	public Airliner(String planeName, int freightAircraftFlyingRange, int airlinerCapacity) {
		super(planeName, freightAircraftFlyingRange);
		this.airlinerCapacity = airlinerCapacity;
	}

	@Override
	public String toString() {
		return "Passenger aircraft: " + getPlaneName()
				+ ". Flying range: " + getFreightAircraftFlyingRange() + "km"
				+ ". Capacity: " + getAirlinerCapacity() + " passengers.";
	}
}
