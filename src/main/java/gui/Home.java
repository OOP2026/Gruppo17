package gui;

import controller.Controller;
import model.didattica.Orario;
import model.utente.Docente;
import model.utente.ResponsabileOrari;
import model.utente.Studente;

import java.util.ArrayList;

public class Home {

	public static void main(String[] args) {

		// MODEL

		Orario orario =
				new Orario();

		ArrayList<Studente> studenti =
				new ArrayList<>();

		ArrayList<Docente> docenti =
				new ArrayList<>();

		ArrayList<ResponsabileOrari> responsabili =
				new ArrayList<>();

		// RESPONSABILE DI DEFAULT

		ResponsabileOrari responsabile =
				new ResponsabileOrari(
						"Mario",
						"Rossi",
						"responsabile@gmail.com",
						"responsabile",
						"1234"
				);

		responsabili.add(responsabile);

		// CONTROLLER

		Controller controller =
				new Controller(
						orario,
						studenti,
						docenti,
						responsabili
				);

		// LOGIN FRAME

		LoginFrame loginFrame =
				new LoginFrame(controller);

		loginFrame.setVisible(true);
	}
}