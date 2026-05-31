package gui;

import controller.Controller;
import model.utente.UserRole;

public class Home {

	public static void main(String[] args) {


		Controller controller = new Controller();


		controller.register(
				"Mario",                  // name
				"Rossi",                  // surname
				"responsabile@gmail.com", // email
				"responsabile",           // login
				"1234",                   // password
				UserRole.ADMIN,           // Наш новий Enum ролі
				null,                     // studentId (для адміна не потрібен)
				null                      // annoCorso (для адміна не потрібен)
		);


		controller.register(
				"Ivan",
				"Ivanov",
				"student@gmail.com",
				"student",
				"1234",
				UserRole.STUDENT,
				"M123456",
				model.didattica.AnnoCorso.PRIMO
		);


		controller.startApplication();
	}
}