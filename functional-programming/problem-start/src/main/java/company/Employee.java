package company;

public class Employee {
	
	private String name;
	private int rank;
	private double salary;
	
	public Employee(String name, int rank, double salary) {
		this.name = name;
		this.rank = rank;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}

	private int getRank() {
		return rank;
	}
	
	public String toString() {
		return name + ", " + rank + ", $" + salary;
	}



}
