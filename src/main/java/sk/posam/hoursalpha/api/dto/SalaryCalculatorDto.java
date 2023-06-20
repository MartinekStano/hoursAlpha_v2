package sk.posam.hoursalpha.api.dto;

public class SalaryCalculatorDto {
    public double salaryPerHour;
    public double totalHours;
    public double levies;
    public double tax;
    public double clearSalary;
    public double totalSalary;
    public double superTotalSalary;

    public SalaryCalculatorDto() {
    }

    public SalaryCalculatorDto(double salaryPerHour, double totalHours, double levies, double tax, double clearSalary, double totalSalary, double superTotalSalary) {
        this.salaryPerHour = salaryPerHour;
        this.totalHours = totalHours;
        this.levies = levies;
        this.tax = tax;
        this.clearSalary = clearSalary;
        this.totalSalary = totalSalary;
        this.superTotalSalary = superTotalSalary;
    }
}
