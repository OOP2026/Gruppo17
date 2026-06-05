package gui;

import controller.Controller;
import model.didattica.AnnoCorso;
import model.utente.UserRole;

public class Home {

	public static void main(String[] args) {

		// 1. Inizializzazione del Controller centrale (Pattern Singleton/Regolatore)
		Controller controller = new Controller();


		// 2. Registrazione manuale di un Responsabile Orari (Admin) aggiuntivo per i test
		controller.register(
				"Mario",
				"Rossi",
				"responsabile@gmail.com",
				"responsabile",
				"1234",
				UserRole.ADMIN,
				null,
				null
		);


		// 3. Registrazione manuale di un Account Studente di test
		controller.register(
				"Ivan",
				"Ivanov",
				"student@gmail.com",
				"student",
				"1234",
				UserRole.STUDENT,
				"M123456",
				AnnoCorso.PRIMO
		);


		// 4. Lancio dell'interfaccia grafica Swing (Apertura automatica del LoginFrame)
		controller.startApplication();
	}
}