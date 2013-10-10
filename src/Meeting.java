import java.util.ArrayList;
import java.util.List;


public class Meeting {
	
	public int name;
	public List<Employee> employees;
	
	public Meeting(int name) {
		this.name = name;
		this.employees = new ArrayList<Employee>();
	}
	
	public boolean addEmployee(Employee emp) {
		for (Employee e : employees) {
			if (e.name == emp.name) {
				return false;
			}
		}
		
		employees.add(emp);
		return true;
	}
	
	@Override
	public String toString() {
		return "" + this.name;
	}

}
