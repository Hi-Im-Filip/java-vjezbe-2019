package hr.java.vjezbe.entitet;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Metode {
	
	public static final int BROJ_PROFESORA = 2;
	public static final int BROJ_PREDMETA = 3;
	public static final int BROJ_ISPITA = 1;
	
	public Profesor[] profesor(Scanner scanner) {
		
		Profesor[] profesor = new Profesor[BROJ_PROFESORA];
		String sifraProfesor = "";	
		String imeProfesor = "";
		String prezimeProfesor = "";
		String titulaProfesora = "";
		int j = 1;
		
		for(int i = 0; i < profesor.length; i++) {
		System.out.println("Unesite " + j + "." + " profesora: ");
        
		String msgProfesorSifra = "Unesite �ifru profesora:  ";
    	sifraProfesor = emptyChecker(scanner, msgProfesorSifra , sifraProfesor);
			
		String msgProfesorName = "Unesite ime profesora: ";
		imeProfesor = emptyChecker(scanner, msgProfesorName, imeProfesor);
				
		String msgProfesorSurname = "Unesite prezime profesora: ";
		prezimeProfesor = emptyChecker(scanner, msgProfesorSurname, prezimeProfesor);
		
		String msgProfesorTitula = "Unesite titulu profesora: ";
		titulaProfesora = emptyChecker(scanner, msgProfesorTitula, titulaProfesora);
		
		j++; 
		profesor[i] = new Profesor(sifraProfesor, imeProfesor, prezimeProfesor, titulaProfesora);		
				
		}
		
		return profesor;
		
	}
	
	
	
	public Predmet[] predmet(Scanner scanner, Profesor[] profesorObjekt) {
			
		Predmet[] predmet = new Predmet[BROJ_PREDMETA];	
		Profesor profesorOdabir;
		String sifraPredmet = "";
		String nazivPredmet = "";
		int j = 1;
		
		
		for(int i = 0; i < predmet.length; i++) {
			    
			    int h = 1;
			    System.out.println("Unesite " + j++ + "." + " Predmet: ");
				
			    String msgPredmetSifra = "Unesite �ifru predmeta: ";
				sifraPredmet = emptyChecker(scanner, msgPredmetSifra, sifraPredmet);
				
				String msgPredmetName = "Unesite naziv predmeta: ";
				nazivPredmet = emptyChecker(scanner, msgPredmetName, nazivPredmet);
				
				System.out.println("Unesite broj ECTS bodova za predmet " + nazivPredmet + ":");
				Integer brojEctsBodovaPredmet = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Odaberite profesora: ");		
				for (Profesor profac : profesorObjekt) {
					System.out.println(h++ + ". " + profac.getIme() + " " + profac.getPrezime());
				}				
				Integer odabirProfesor = scanner.nextInt();
				scanner.nextLine();
				
				while (odabirProfesor > profesorObjekt.length || odabirProfesor == 0) {
					System.out.println("Nedozvoljen broj");
					System.out.println("Odaberite profesora: ");
					odabirProfesor = scanner.nextInt();
					scanner.nextLine();
				}
								
				profesorOdabir = profesorObjekt[odabirProfesor - 1];
				System.out.println("Va� odabir profesora je " + odabirProfesor + "." + profesorObjekt[odabirProfesor - 1].getIme() 
							             + " " + profesorObjekt[odabirProfesor - 1].getPrezime());
									
				
				System.out.println("Unesite broj studenta za predmet " + nazivPredmet + ":");
				Integer studentPredmetInt = scanner.nextInt();
				scanner.nextLine();
				Student[] studentPredmet = new Student[studentPredmetInt];
				
				predmet[i] = new Predmet(sifraPredmet, nazivPredmet, brojEctsBodovaPredmet, profesorOdabir, studentPredmet);
							
				  
			}			
	
		return predmet;
	}
		
	public Student[] student(Scanner scanner, Predmet[] predmet) {
		
		int sum = 0;
		int j = 1;
		
		for (Predmet predmetObjekt : predmet) {
			int lengthStudentObjArray = predmetObjekt.getStudent().length;
			  sum = lengthStudentObjArray + sum;
		}
				
		Student[] student = new Student[sum];
				
		for(int i = 0; i < student.length; i++) {
			System.out.println("Unesite " + j + "." + " studenta:");
			System.out.println("Unesite ime studenta: ");
			String imeStudent = scanner.nextLine();
			System.out.println("Unesite prezime studenta: ");
			String prezimeStudent = scanner.nextLine();
			System.out.println("Unesite datum ro�enja za studenta " + prezimeStudent + " " + imeStudent + " u formatu (dd.MM.yyyy.)");
			String datumRodenjaStudentaString = scanner.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			LocalDate datumRodenjaStudenta = LocalDate.parse(datumRodenjaStudentaString, formatter);
			System.out.println("Unesite JMBAG studenta: ");
			String jmbagStudent = scanner.nextLine();
			
			j++;
		    student[i] = new Student(imeStudent, prezimeStudent, jmbagStudent, datumRodenjaStudenta);
			}
		
		return student;
	}

	public Ispit[] ispit(Scanner scanner, Predmet[] predmet, Student[] student) {
		
		Predmet predmetIspita;
		Student studentIspita;
		Ispit[] ispit = new Ispit[BROJ_ISPITA];
		int s = 1;
		int p = 1;
				
		for (int i = 0; i < ispit.length; i++) {
					
		System.out.println("Unesite ispitni rok: ");
		System.out.println("Odaberite predmet: ");		
		for (Predmet predmetObj : predmet) {
			System.out.println(p++ + ". " + predmetObj.getNaziv());
		}
		
		Integer odabirPredmeta = scanner.nextInt();
		scanner.nextLine();
		
		if (odabirPredmeta <= p) {
			predmetIspita = predmet[odabirPredmeta - 1];
			System.out.println("Va� odabir predmeta je " + odabirPredmeta + "." + predmet[odabirPredmeta - 1].getNaziv());
		} else {
			predmetIspita = predmet[0];
			System.out.println("Morate odabrati jedan od ponu�enih predmeta. Dodijeljena je standardna vrijednost predmeta.");
		}
				
		System.out.println("Odaberite studenta: ");
		for (Student studentObj : student) {
				System.out.println(s++ + ". " + studentObj.getIme() + " " + studentObj.getPrezime());
		}
				
		Integer odabirStudenta = scanner.nextInt();
		scanner.nextLine();
		
		if (odabirStudenta <= s) {
			studentIspita = student[odabirStudenta - 1];
			System.out.println("Va� odabir studenta " + odabirStudenta + "." + student[odabirStudenta - 1].getIme() + " " + student[odabirStudenta - 1].getPrezime());
		} else {
			studentIspita = student[0];
			System.out.println("Morate odabrati ne�to od ponu�enog. Dodijeljena je standardna vrijednost.");
		}
						
		System.out.println("Unesite ocjenu na ispitu (1-5): ");
		Integer ocjenaIspita = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm):");		
		String datumIVrijemeIspitString = scanner.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm"); 
		LocalDateTime datumIVrijemeIspita = LocalDateTime.parse(datumIVrijemeIspitString, formatter);
				
		ispit[i] = new Ispit(predmetIspita, studentIspita, ocjenaIspita, datumIVrijemeIspita);
		
		ocjenaStudenta(ocjenaIspita, studentIspita, predmetIspita);
		
		}
				
		return ispit;		
		
	}
	
	public void ocjenaStudenta(Integer ocijena, Student studentIspit, Predmet predmetIspit) {
		
		String ocjenaIspitString;
		
		switch (ocijena) {
		case 1:
			 ocjenaIspitString = "nedovoljan";
			break;
		case 2:
			 ocjenaIspitString = "dovoljan";
			break;
		case 3:
			 ocjenaIspitString = "dobar";
			break;
		case 4:
			 ocjenaIspitString = "vrlo dobar";
			break;
		case 5:
			 ocjenaIspitString = "odli�an";
			break;
		default:
			ocjenaIspitString = "nedovoljan";
			break;
		}
		
		if (ocijena == 5) {
			System.out.println("Student " + studentIspit.getIme() + " " + studentIspit.getPrezime() + 
	                " je ostvario ocjenu " + ocjenaIspitString + " na predmetu " + predmetIspit.getNaziv());
		} else {
			System.out.println("Nema� pojma.");
		}
		
	}
	
	public String emptyChecker(Scanner scanner, String message, String scanString) {
						
		System.out.println(message);
		scanString = scanner.nextLine();
			
		while (scanString.length() == 0) {
				
			System.out.println("Prazno polje!");
			System.out.println(message);
			scanString = scanner.nextLine();
		}
			
		return scanString;
	}
	
	public Integer emptyChecker(Scanner scanner, String message, Integer scanInteger) {

		String scanString;
		System.out.println(message);		
		scanString = scanner.nextLine();
					
		while (scanString.length() == 0) {
				
			System.out.println("Prazno polje!");
			System.out.println(message);
			scanInteger = scanner.nextInt();
		}
		scanInteger = Integer.valueOf(scanString);
		return scanInteger;

	}
}
	
