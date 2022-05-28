public class Position {
    private final int projId;
    private final int empId;
    private final int workload;
    private final String billing;
    public Position(int projId, int empId, int workload, String billing) {
        this.projId = projId;
        this.empId = empId;
        this.workload = workload;
        this.billing = billing;
    }
    public int getWorkload() {
        return workload;
    }
    public int getEmpId() {
        return empId;
    }
    public int getProjId() {
        return projId;
    }
    public String getBilling() {
        return billing;
    }
}
