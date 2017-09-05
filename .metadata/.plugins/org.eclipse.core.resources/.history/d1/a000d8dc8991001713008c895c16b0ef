package planes;

import java.util.List;

public class AirlineCompany {
	private String companyName;
	private List <Plane> planes;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Plane> getPlanes() {
		for(Plane item : planes){
			System.out.println(item);
		}
		return planes;
	}
	
	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public AirlineCompany(String companyName, List<Plane> planes) {
		super();
		this.companyName = companyName;
		this.planes = planes;
	}

	@Override
	public String toString() {
		return "Авиакомпания " + companyName + "." + "\n" + "Парк самолётов:";
	}
	
}
