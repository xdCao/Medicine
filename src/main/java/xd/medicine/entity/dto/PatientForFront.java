package xd.medicine.entity.dto;

/**
 * created by liubotao
 */
public class PatientForFront {
    private PatientWithTrust patientWithTrust;
    private boolean isMyPatient;

    public PatientForFront(PatientWithTrust patientWithTrust, boolean isMyPatient) {
        this.patientWithTrust = patientWithTrust;
        this.isMyPatient = isMyPatient;
    }

    public PatientWithTrust getPatientWithTrust() {
        return patientWithTrust;
    }

    public void setPatientWithTrust(PatientWithTrust patientWithTrust) {
        this.patientWithTrust = patientWithTrust;
    }

    public boolean isMyPatient() {
        return isMyPatient;
    }

    public void setMyPatient(boolean myPatient) {
        isMyPatient = myPatient;
    }
}
