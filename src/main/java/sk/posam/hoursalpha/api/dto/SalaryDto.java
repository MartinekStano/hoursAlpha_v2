package sk.posam.hoursalpha.api.dto;

public class SalaryDto {
    public double totalSalary;
    public double clearSalary;
    public double totalHours;

    public SalaryDto(double totalSalary, double clearSalary, double totalHours) {
        this.totalSalary = totalSalary;
        this.clearSalary = clearSalary;
        this.totalHours = totalHours;
    }
}
