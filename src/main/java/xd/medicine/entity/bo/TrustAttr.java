package xd.medicine.entity.bo;

public class TrustAttr {
    private Integer id;

    private Byte department;

    private Byte demandTitle;

    private Byte demandWorkage;

    private Byte demandDegree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getDepartment() {
        return department;
    }

    public void setDepartment(Byte department) {
        this.department = department;
    }

    public Byte getDemandTitle() {
        return demandTitle;
    }

    public void setDemandTitle(Byte demandTitle) {
        this.demandTitle = demandTitle;
    }

    public Byte getDemandWorkage() {
        return demandWorkage;
    }

    public void setDemandWorkage(Byte demandWorkage) {
        this.demandWorkage = demandWorkage;
    }

    public Byte getDemandDegree() {
        return demandDegree;
    }

    public void setDemandDegree(Byte demandDegree) {
        this.demandDegree = demandDegree;
    }
}