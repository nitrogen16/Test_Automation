package task1_object_model.planes;

public abstract class Plane {
	
	private String planeName;
	private int freightAircraftFlyingRange;
	
	public String getPlaneName() {
		return planeName;
	}
	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}
	public int getFreightAircraftFlyingRange() {
		return freightAircraftFlyingRange;
	}
	public void setFreightAircraftFlyingRange(int freightAircraftFlyingRange) {
		this.freightAircraftFlyingRange = freightAircraftFlyingRange;
	}
	
	public Plane(String planeName, int freightAircraftFlyingRange) {
		super();
		this.planeName = planeName;
		this.freightAircraftFlyingRange = freightAircraftFlyingRange;
	}
	
}
