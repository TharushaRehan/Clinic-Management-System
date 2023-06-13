import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CourseworkGUI extends JFrame{

    // create swing components and make them private
    private JFrame doctors,consultation,visualiseFrame,removeFrame;
    private JTable doctorTable;
    private JButton seeDoctors,addConsultation,removeConsultation,sort,availability, clear,book,visualise,search,remove;
    private String[][] doctorsList;
    private ArrayList<Doctor> doctorArray;
    private JLabel header,header1,name,surname,dob,mobileNumber,pID,doctorsName,conDate,conTime,cost, header2,notes, conHour, visualiseLabel,removeLabel;
    private JTextField nameTextField,surnameTextField,dobTextField, mNumberTextField,IDTextField, cDateField,cTime,costField,noteField, conHoursField, visPaID,removeID;
    private JList<String> list;

    // create font styles for buttons and JLabels
    Font buttonFont = new Font("Times New Roman",Font.BOLD,20);
    String[] columnNames = {"First Name","Surname","Date of Birth","Mobile Number","Medical License Number","Specialisation"};
    Font headerFont = new Font("Times New Roman",Font.BOLD,20);
    Font normalFont = new Font("Times New Roman",Font.PLAIN,16);

    // create necessary arraylists
    ArrayList<Patient> patientsList = new ArrayList<>();
    ArrayList<Consultation> consultationsList = new ArrayList<>();
    DefaultListModel<String> nameList;


    public CourseworkGUI(String[][] myDoctors, ArrayList<Doctor> doctorArray1){
        // load data from consultation file and patient file when start the GUI again
        loadConData();
        loadPatientData();

        setLayout(null);
        doctorsList=myDoctors;
        doctorArray=doctorArray1;

        // add see the list of doctors button to the frame and set font styles
        seeDoctors = new JButton("See the List of Doctors");
        seeDoctors.setFont(buttonFont);
        seeDoctors.setBounds(280,80,250,80);
        seeDoctors.setBackground(new Color(169, 204, 227 ));
        add(seeDoctors);
        // add consultation button to the frame and set font styles
        addConsultation = new JButton("Add a Consultation");
        addConsultation.setBackground(new Color(169, 204, 227 ));
        addConsultation.setBounds(280,230,250,80);
        addConsultation.setFont(buttonFont);
        add(addConsultation);
        // add delete consultation button to the frame and set font styles
        removeConsultation = new JButton("Delete a Consultation");
        removeConsultation.setFont(buttonFont);
        removeConsultation.setBounds(280,380,250,80);
        removeConsultation.setBackground(new Color(169, 204, 227 ));
        add(removeConsultation);

        ButtonHandler handler = new ButtonHandler();   // create button handler object and pass buttons to the handler
        seeDoctors.addActionListener(handler);
        addConsultation.addActionListener(handler);
        removeConsultation.addActionListener(handler);

        sort = new JButton("Sort");                             // create a sort button
        sort.setPreferredSize(new Dimension(40,40));
        sort.addActionListener(handler);

        conHoursField = new JTextField(10);
        conHoursField.addActionListener(handler);

        availability = new JButton("Check Availability");           // create button to check availability of a doctor
        availability.addActionListener(handler);
        availability.setFont(buttonFont);
        availability.setBackground(new Color(169, 204, 227 ));

        clear = new JButton("Clear");                              // create button to clear entered data
        clear.addActionListener(handler);
        clear.setFont(buttonFont);
        clear.setBackground(new Color(169, 204, 227 ));

        book = new JButton("Book");                               // create a button to book a consultation
        book.addActionListener(handler);
        book.setFont(buttonFont);
        book.setBackground(new Color(169, 204, 227 ));

        visualise = new JButton("Visualise");                        // create visualise button to see the consultation details
        visualise.addActionListener(handler);
        visualise.setFont(buttonFont);
        visualise.setBackground(new Color(169, 204, 227 ));

        search = new JButton("Search");                                 // create a search button
        search.addActionListener(handler);
        search.setFont(normalFont);
        search.setBackground(new Color(169, 204, 227 ));

        remove = new JButton("Delete");                    // create a delete button
        remove.addActionListener(handler);
        remove.setFont(normalFont);
        remove.setBackground(new Color(169, 204, 227 ));

    }


    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent event){
            if (event.getSource()==seeDoctors){                       // if user click see the list of doctors button, run this code
                doctors = new JFrame("List of Doctors");           // create a new frame
                doctors.setLayout(null);
                doctorTable = new JTable(doctorsList, columnNames);       // create a JTable
                doctorTable.setBounds(80, 60, 400, 500);
                doctorTable.setFont(new Font("Times New Roman",Font.PLAIN,14));              // set font styles
                doctorTable.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,16));
                JScrollPane sp =new JScrollPane(doctorTable);
                sp.setBounds(40,30,1100,400);
                doctors.add(sp);

                sort.setBounds(500,500,100,40);
                doctors.add(sort);
                sort.setBackground(new Color(169, 204, 227));
                sort.setFont(buttonFont);


                doctors.setSize(1200,700);                                    // set frame size,visible and background colour
                doctors.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                doctors.setVisible(true);
                doctors.getContentPane().setBackground(new Color(170, 183, 184 ));

            }
            if (event.getSource()==sort){                                          // if user click sort button, run this code
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(doctorTable.getModel());
                doctorTable.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();

                int columnIndex = 1;
                sortKeys.add(new RowSorter.SortKey(columnIndex,SortOrder.ASCENDING));  // sort by ascending order

                sorter.setSortKeys(sortKeys);
                sorter.sort();
            }
            if (event.getSource()==addConsultation){                // if user click add consultation button, run this code
                consultation = new JFrame("Add a consultation");           // create a new frame and set background colour
                consultation.getContentPane().setBackground(new Color(170, 183, 184 ));
                consultation.setLayout(null);

                // add font styles to the components and add them to the frame
                header = new JLabel("Patient Details");
                consultation.add(header);
                header.setFont(headerFont);

                name = new JLabel("Name");
                name.setFont(normalFont);
                consultation.add(name);
                nameTextField = new JTextField(20);
                consultation.add(nameTextField);


                surname = new JLabel("Surname");
                surname.setFont(normalFont);
                consultation.add(surname);
                surnameTextField = new JTextField(20);
                consultation.add(surnameTextField);

                dob = new JLabel("Date of Birth");
                dob.setFont(normalFont);
                consultation.add(dob);
                dobTextField = new JTextField(10);
                consultation.add(dobTextField);

                mobileNumber = new JLabel("Mobile Number");
                mobileNumber.setFont(normalFont);
                consultation.add(mobileNumber);
                mNumberTextField = new JTextField(10);
                consultation.add(mNumberTextField);

                pID = new JLabel("Patient ID");
                pID.setFont(normalFont);
                consultation.add(pID);
                IDTextField = new JTextField(10);
                consultation.add(IDTextField);

                header1 = new JLabel("Select a Doctor");
                header1.setFont(headerFont);
                consultation.add(header1);

                doctorsName = new JLabel("Doctor's Name");
                doctorsName.setFont(normalFont);
                consultation.add(doctorsName);

                nameList = new DefaultListModel<>();
                for (int i=0;i<doctorArray.size();i++){
                    nameList.addElement(doctorArray.get(i).getName()+" "+doctorArray.get(i).getSurname());
                }
                list = new JList<>(nameList);                               // create a JList, user can select only one item
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setSelectedIndex(0);
                //list.setVisibleRowCount(1);
                list.setFont(normalFont);
                JScrollPane pane = new JScrollPane(list);
                pane.setBounds(450,110,150,60);
                consultation.add(pane);

                // add font styles to the components and add them to the frame
                conDate = new JLabel("Consultation Date");
                conDate.setFont(normalFont);
                consultation.add(conDate);

                cDateField = new JTextField(10);
                consultation.add(cDateField);

                conTime = new JLabel("Consultation Time");
                conTime.setFont(normalFont);
                consultation.add(conTime);

                cTime = new JTextField(10);
                consultation.add(cTime);

                header2 = new JLabel("Consultation");
                header2.setFont(headerFont);
                consultation.add(header2);

                conHour = new JLabel("Hours");
                conHour.setFont(normalFont);
                consultation.add(conHour);
                consultation.add(conHoursField);

                cost = new JLabel("Cost");
                cost.setFont(normalFont);
                consultation.add(cost);
                costField = new JTextField(10);
                consultation.add(costField);

                notes = new JLabel("Notes");
                notes.setFont(normalFont);
                consultation.add(notes);
                noteField = new JTextField(10);
                consultation.add(noteField);

                // add necessary buttons to the frame and add font style
                availability.setFont(normalFont);
                consultation.add(availability);

                clear.setFont(normalFont);
                consultation.add(clear);

                book.setFont(normalFont);
                consultation.add(book);

                visualise.setFont(normalFont);
                consultation.add(visualise);


                setLocations();                // load set location method to set components location in the frame
                consultation.setSize(1600,600);                  // set frame size,visible
                consultation.setVisible(true);
                consultation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

            if (event.getSource()==removeConsultation){         // if user click delete consultation button, run this code
                removeFrame = new JFrame("Delete a Consultation");    // create a new frame
                removeFrame.setLayout(null);

                // create components and place them in the frame
                removeLabel = new JLabel("Enter Patient ID");
                removeID = new JTextField(10);
                removeLabel.setBounds(30,20,150,30);
                removeLabel.setFont(normalFont);
                removeID = new JTextField(10);
                removeID.setBounds(200,24,150,20);
                remove.setBounds(390,24,150,20);
                // add components to the frame
                removeFrame.add(removeLabel);
                removeFrame.add(removeID);
                removeFrame.add(remove);

                removeFrame.setSize(800,500);                              // set frame size,visible and background colour
                removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                removeFrame.setVisible(true);
                removeFrame.getContentPane().setBackground(new Color(170, 183, 184 ));
            }
            if (event.getSource()==remove){                          // if user click remove button to delete a consultation, run this code
                if (consultationsList.size()>0) {
                    int idx=-1;
                    String ID = removeID.getText();                     // get patient ID as a string
                    for (int i=0;i<consultationsList.size();i++) {
                        if (ID.equalsIgnoreCase(consultationsList.get(i).getPatientID())){   // check whether there is a consultation for that patient
                            idx=i;
                            break;
                        }
                    }
                    if (idx>=0){                                // if there is consultation remove that consultation
                        consultationsList.remove(idx);
                        JOptionPane.showMessageDialog(removeFrame, "Deleted Consultation");
                        try {
                            Writer myWriter = new FileWriter("Consultation.txt");   // write to the text file after delete a  consultation
                            for (int j=0;j<consultationsList.size();j++){
                                String cost1 = consultationsList.get(j).getCost();
                                String notes1 = consultationsList.get(j).getNotes();
                                String date1 = consultationsList.get(j).getDate();
                                String patientID1 = consultationsList.get(j).getPatientID();
                                String dName = consultationsList.get(j).getDocName();
                                myWriter.write(cost1+"\n"+notes1+"\n"+date1+"\n"+patientID1+"\n"+dName+"\n");
                            }
                            myWriter.close();
                        } catch (IOException e) {
                            System.out.println("An error occurred.\n");
                        }
                    }
                    if (idx==-1){             // if user give invalid patient id, display this message
                        JOptionPane.showMessageDialog(removeFrame, "Invalid Patient ID");
                    }
                }
                else {                 // if there are no consultation at the moment, display this message
                    JOptionPane.showMessageDialog(removeFrame, "No Consultations.");
                }

            }

            if (event.getSource()==availability) {         // if user click availability button to check a doctor is available or not, run this code
                String doctor = list.getSelectedValue();       // get selected value from JList
                String fullDate = cDateField.getText() + " - " + cTime.getText();   // get consultation date and time
                if (consultationsList.size() > 0) {
                    for (int i = 0; i < consultationsList.size(); i++) {
                        // check whether there is a scheduled consultation for that doctor
                        if (consultationsList.get(i).getDocName().equals(doctor) && consultationsList.get(i).getDate().equals(fullDate)) {
                            JOptionPane.showMessageDialog(consultation, "Doctor is not Available");
                            while (true){
                                Random random = new Random();
                                String randName = nameList.get(random.nextInt(nameList.size()));   // randomly select a doctor
                                if (!doctor.equals(randName)) {
                                    int index = nameList.indexOf(randName);
                                    list.setSelectedIndex(index);          // select randomly generated name on the JList
                                    JOptionPane.showMessageDialog(consultation, "Doctor " + randName + " will add as the doctor.");
                                    break;
                                }
                            }
                        } else {         // if there is no scheduled consultation for that doctor, display this message
                            JOptionPane.showMessageDialog(consultation, "Doctor is Available");
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(consultation, "Doctor is Available");
                }
            }

            if (event.getSource()==book){            // if user click book button to schedule a consultation, run this code
                // get values from text-fields
                String pName = nameTextField.getText();
                String surname = surnameTextField.getText();
                String pDOB = dobTextField.getText();
                String number = mNumberTextField.getText();
                String patientID = IDTextField.getText();
                String conDate1 = cDateField.getText();
                String conTime1 = cTime.getText();
                String cost = costField.getText();
                String notes = noteField.getText();
                String hours = conHoursField.getText();
                String sDate="",sDate1="",sTime="";

                // check whether all the textfileds are empty,mobile number should have 10 numbers
                if (pName.isEmpty() || surname.isEmpty()|| pDOB.isEmpty() || number.isEmpty() || patientID.isEmpty() || conDate1 .isEmpty()
                        || conTime1.isEmpty() || notes.isEmpty() || hours.isEmpty() || number.length()!=10 || pDOB.length()>0 || conDate1.length()>0 ||
                        conTime1.length()>0 ){
                    if (pName.isEmpty() || surname.isEmpty()|| pDOB.isEmpty() || number.isEmpty() || patientID.isEmpty() || conDate1 .isEmpty()
                            || conTime1.isEmpty() || notes.isEmpty() || hours.isEmpty()){
                        JOptionPane.showMessageDialog(consultation,"Fill all the inputs.");// if textfields are empty display this message
                    }
                    else if (number.length()!=10) {        // if mobile number doesn't have 10 numbers, display this message
                        JOptionPane.showMessageDialog(consultation, "Mobile Number must have 10 digits.");
                    }
                    else if (pDOB.length()>0 || conDate1.length()>0 || conTime1.length()>0){  // check whether date format is correct, if not display message
                        try {
                            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(pDOB);
                            sDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
                            //System.out.println(sDate);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(consultation, dob.getText() + " - Invalid Format(Year/Month/Date)");
                        }
                        try {
                            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(conDate1);
                            sDate1 = new SimpleDateFormat("yyyy/MM/dd").format(date1);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(consultation, conDate.getText() + " - Invalid Format(Year/Month/Date)");
                        }
                        try {
                            Date time = new SimpleDateFormat("hh/mm").parse(conTime1);
                            sTime = new SimpleDateFormat("yyyy/MM/dd").format(time);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(consultation, conTime.getText() + " - Invalid Format(Hour/Minutes)");
                        }
                    }

                }
                // if all the textfileds are not empty, run this code
                if (!pName.isEmpty() && !surname.isEmpty() && number.length()==10 && !patientID.isEmpty() && !notes.isEmpty() && !hours.isEmpty()
                && !sDate.isEmpty() && !sDate1.isEmpty() && !sTime.isEmpty() ) {
                    String doctor = list.getSelectedValue();
                    String fullDate = cDateField.getText() + " - " + cTime.getText();
                    Consultation c1 = new Consultation(cost, notes, fullDate, patientID, doctor); // create a consultation and add to the list
                    consultationsList.add(c1);
                    Patient p1 = new Patient(pName, surname, pDOB, number, patientID);  // create a patient and add to the list
                    patientsList.add(p1);
                    try {
                        Writer myWriter = new FileWriter("Patients.txt");    // write patient data to a text file
                        //myWriter.write(pName + "\n" + surname + "\n" + pDOB + "\n" + number + "\n" + patientID + "\n");
                        for (int i=0;i<patientsList.size();i++){
                            String name1 = patientsList.get(i).getName();
                            String surname1 = patientsList.get(i).getSurname();
                            String dob1 = patientsList.get(i).getDob();
                            String mNumber = patientsList.get(i).getMobileNumber();
                            String patientID1 = patientsList.get(i).getPatientID();
                            myWriter.write(name1+"\n"+surname1+"\n"+dob1+"\n"+mNumber+"\n"+patientID1+"\n");

                        }
                        myWriter.close();
                        //JOptionPane.showMessageDialog(consultation,"Successfully saved to the system.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.\n");
                    }

                    try {
                        Writer myWriter = new FileWriter("Consultation.txt");   // write consultation data to a text file
                        for (int i=0;i<consultationsList.size();i++){
                            String cost1 = consultationsList.get(i).getCost();
                            String notes1 = consultationsList.get(i).getNotes();
                            String date1 = consultationsList.get(i).getDate();
                            String patientID1 = consultationsList.get(i).getPatientID();
                            String dName = consultationsList.get(i).getDocName();

                            myWriter.write(cost1+"\n"+notes1+"\n"+date1+"\n"+patientID1+"\n"+dName+"\n");

                        }
                        myWriter.close();
                        JOptionPane.showMessageDialog(consultation, "Successfully saved to the system.");
                        //System.out.println("Successfully saved to a file.\n");
                    } catch (IOException e) {
                        System.out.println("An error occurred.\n");

                    }
                }

            }

            // when user clicks on clear button, set text-fields values to empty
            if (event.getSource()== clear){
                nameTextField.setText("");
                surnameTextField.setText("");
                dobTextField.setText("");
                mNumberTextField.setText("");
                IDTextField.setText("");
                list.setSelectedIndex(0);
                cDateField.setText("");
                cTime.setText("");
                conHoursField.setText("");
                costField.setText("");
                noteField.setText("");
            }

            /* when user enter number of hours for the consultation, calculate the cost and each consultation is £25
             per hour and the first consultation is £15 per hour*/
            if (event.getSource()== conHoursField){
                int fCon = 15;
                int sCon = 25;
                String ID = IDTextField.getText();
                if (consultationsList.size()>0) {
                    try {
                        double time = Double.parseDouble(conHoursField.getText());
                        for (int i = 0; i < patientsList.size(); i++) {
                            if (patientsList.get(i).getPatientID().equals(ID)) {
                                double totCost = time * sCon;
                                costField.setText(String.valueOf(totCost));
                            } else {
                                double totCost1 = time * fCon;
                                costField.setText(String.valueOf(totCost1));
                            }
                        }
                    } catch (NumberFormatException e) {  // If user enters something other than a number, this message will display on the frame
                        JOptionPane.showMessageDialog(consultation, "Hour should be a number (Hour or Hour.Minute).");
                    }
                }
                else {
                    try {
                        double time = Double.parseDouble(conHoursField.getText());
                        double totCost1 = time * fCon;
                        costField.setText(String.valueOf(totCost1));
                    } catch (NumberFormatException e) {  // If user enters something other than a number, this message will display on the frame
                        JOptionPane.showMessageDialog(consultation, "Hour should be a number (Hour or Hour.Minute).");
                    }
                }
            }

            if (event.getSource()==visualise){            // if user click visualise button, run this code
                visualiseFrame = new JFrame("Visualise");       // create a new frame
                visualiseFrame.setLayout(null);
                // create components and add font styles
                visualiseLabel = new JLabel("Enter Patient ID");
                visualiseLabel.setBounds(30,20,150,30);
                visualiseLabel.setFont(normalFont);
                visPaID = new JTextField(10);
                visPaID.setBounds(200,24,150,20);
                search.setBounds(390,24,150,20);
                // add components to the frame
                visualiseFrame.add(search);
                visualiseFrame.add(visualiseLabel);
                visualiseFrame.add(visPaID);

                visualiseFrame.setSize(800,500);               // set frame size,visible and background colour
                visualiseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                visualiseFrame.setVisible(true);
                visualiseFrame.getContentPane().setBackground(new Color(170, 183, 184 ));
            }
            if (event.getSource()==search){                        // if user click visualise button, run this code
                if (patientsList.size()>0) {
                    for (int i = 0; i < patientsList.size(); i++) {
                        if (visPaID.getText().equals(patientsList.get(i).getPatientID())) {     // if there is patient who has that patient id, display consultation details
                            int index = consultationsList.indexOf(consultationsList.get(i));
                            JOptionPane.showMessageDialog(visualiseFrame, "Patient Details \n" + "Name - " + patientsList.get(i).getName() + "\n" +
                                    "Surname - " + patientsList.get(i).getSurname() + "\n" +
                                    "Date of Birth - " + patientsList.get(i).getDob() + "\n" +
                                    "Mobile Number - " + patientsList.get(i).getMobileNumber() + "\n" +
                                    "Patient ID - " + patientsList.get(i).getPatientID() + "\n" +
                                    "Doctor's Name - " + consultationsList.get(index).getDocName() + "\n" +
                                    "Consultation Date - " + consultationsList.get(index).getDate() + "\n" +
                                    "Consultation Cost - " + consultationsList.get(index).getCost() + "\n" +
                                    "Notes - " + consultationsList.get(index).getNotes());
                            break;
                        }
                        else {   // if user entered invalid patient id, display this message
                            JOptionPane.showMessageDialog(visualiseFrame, "Invalid Patient ID.");
                            break;
                        }
                    }
                }
                else {     // if there is no patient, display this message
                    JOptionPane.showMessageDialog(visualiseFrame, "No Patients in the Centre.");
                }
            }
        }
    }

    // Place components in the consultation frame using setBounds attribute.
    public void setLocations(){
        header.setBounds(20,20,150,30);
        name.setBounds(20,70,100,20);
        surname.setBounds(20,100,100,20);
        dob.setBounds(20,130,100,20);
        mobileNumber.setBounds(20,160,100,20);
        pID.setBounds(20,190,100,20);
        nameTextField.setBounds(180,70,150,20);
        surnameTextField.setBounds(180,100,150,20);
        dobTextField.setBounds(180,130,150,20);
        mNumberTextField.setBounds(180,160,150,20);
        IDTextField.setBounds(180,190,150,20);

        header1.setBounds(450,20,150,30);
        doctorsName.setBounds(450,70,100,20);
        conDate.setBounds(680,90,150,20);
        cDateField.setBounds(800,90,150,20);
        conTime.setBounds(680,130,150,20);
        cTime.setBounds(800,130,150,20);
        availability.setBounds(600,200,150,25);

        header2.setBounds(1100,20,150,30);
        conHour.setBounds(1100,70,100,20);
        conHoursField.setBounds(1150,70,150,20);
        cost.setBounds(1100,100,100,20);
        costField.setBounds(1150,100,150,20);
        notes.setBounds(1100,130,100,20);
        noteField.setBounds(1150,130,150,20);

        clear.setBounds(500,350,100,40);
        book.setBounds(650,350,100,40);
        visualise.setBounds(800,350,150,40);
    }

    // load existing data from the consultation file, when user starts the GUI again
    public void loadConData(){
        int lineCount = 0;
        ArrayList<String> conDetails = new ArrayList<>();
        String cost,notes,date,patientID,dName;
        int firstIndex=0;
        int secIndex=1;
        int thIndex=2;
        int foIndex=3;
        int fifIndex=4;
        try{
            File inputFile = new File("Consultation.txt");
            Scanner rf = new Scanner(inputFile);
            String fileLine;
            while(rf.hasNext()){
                fileLine= rf.nextLine();
                conDetails.add(fileLine);
                lineCount=lineCount+1;
            }
            System.out.print("\n");
            rf.close();
            for (int i=0;i<conDetails.size()/5;i++){
                cost=conDetails.get(firstIndex);
                notes=conDetails.get(secIndex);
                date=conDetails.get(thIndex);
                patientID=conDetails.get(foIndex);
                dName=conDetails.get(fifIndex);
                createConsultation(cost,notes,  date, patientID, dName);  // load create  consultation method
                firstIndex=firstIndex+5;                      // update index value
                secIndex=secIndex+5;
                thIndex=thIndex+5;
                foIndex=foIndex+5;
                fifIndex=fifIndex+5;
            }
            System.out.println("Successfully Loaded Consultation Data.");
            System.out.println("==============================================");
        }
        catch (IOException e){
            System.out.println("File is not exists.");
            System.out.println("==============================================");

        }
    }

    // load existing data from the patient file, when user starts the GUI again
    public void loadPatientData(){
        int lineCount = 0;
        ArrayList<String> paDetails = new ArrayList<>();
        String name1,surname1,dob1,mobNumber, patientId;

        int firstIndex=0;
        int secIndex=1;
        int thIndex=2;
        int foIndex=3;
        int fifIndex=4;
        try{
            File inputFile = new File("Patients.txt");
            Scanner rf = new Scanner(inputFile);
            String fileLine;
            while(rf.hasNext()){
                fileLine= rf.nextLine();
                paDetails.add(fileLine);
                lineCount=lineCount+1;
            }
            rf.close();
            for (int i=0;i<paDetails.size()/5;i++){
                name1=paDetails.get(firstIndex);
                surname1=paDetails.get(secIndex);
                dob1=paDetails.get(thIndex);
                mobNumber=paDetails.get(foIndex);
                patientId=paDetails.get(fifIndex);
                createPatient(name1,surname1,dob1,mobNumber, patientId);       // load create patient method
                firstIndex=firstIndex+5;                                            // update index value
                secIndex=secIndex+5;
                thIndex=thIndex+5;
                foIndex=foIndex+5;
                fifIndex=fifIndex+5;
            }
            System.out.println("Successfully Loaded Patients Data.");
            System.out.println("==============================================");
        }
        catch (IOException e){
            System.out.println("File is not exists.");
            System.out.println("==============================================");
        }
    }

    // Create a consultation
    public void createConsultation(String cost,String notes,String date, String patientID, String dName){
        Consultation c1 = new Consultation(cost,notes,date,patientID,dName);
        consultationsList.add(c1);
    }

    // Create a patient
    public void createPatient(String name1, String surname1, String dob1, String mobNumber,String patientId){
        Patient p1 = new Patient(name1,surname1,dob1,mobNumber,patientId);
        patientsList.add(p1);
    }
}
