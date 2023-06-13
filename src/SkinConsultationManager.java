import java.util.ArrayList;

interface SkinConsultationManager {

    void addNewDoctor(ArrayList<Doctor> doctor, ArrayList<Person> persons);
    void deleteDoctor(ArrayList<Doctor> doctor);
    void printDoctors(ArrayList<Doctor> doctor,ArrayList<Doctor> printDoctor);
    void saveToFile(ArrayList<Doctor> doctor);

}
