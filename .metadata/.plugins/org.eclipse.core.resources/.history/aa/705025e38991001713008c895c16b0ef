package planes;

public class FreightAircraft extends Plane{
	
	private double planeFreight;

	public double getPlaneFreight() {
		return planeFreight;
	}

	public void setPlaneFreight(double planeFreight) {
		this.planeFreight = planeFreight;
	}

	public FreightAircraft(String planeName, int freightAircraftFlyingRange) {
		super(planeName, freightAircraftFlyingRange);
	}

	public FreightAircraft(String planeName, int freightAircraftFlyingRange,
			double planeFreight) {
		super(planeName, freightAircraftFlyingRange);
		this.planeFreight = planeFreight;
	}

	@Override
	public String toString() {
		return "Грузовой: " + getPlaneName()
				+ ". Дальность полёта: " + getFreightAircraftFlyingRange() + "км"
				+ ". Грузоподъёмность: " + getPlaneFreight() + " тонн.";
	}
	
	
}
