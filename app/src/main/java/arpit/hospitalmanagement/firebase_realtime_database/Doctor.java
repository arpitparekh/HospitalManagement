package arpit.hospitalmanagement.firebase_realtime_database;

public class Doctor {

    String name;
    String degree;
    String field;
    String hospitalName;

    Doctor(){

    }

    public Doctor(String name, String degree, String field, String hospitalName) {
        this.name = name;
        this.degree = degree;
        this.field = field;
        this.hospitalName = hospitalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
