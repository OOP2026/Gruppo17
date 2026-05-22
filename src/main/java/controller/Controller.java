package controller;

import model.didattica.*;
import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;
import model.richiesta.StatoRichiesta;
import model.utente.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {

	// =====================================================
	// MODEL
	// =====================================================

	private final Orario orario;

	// =====================================================
	// SESSIONE
	// =====================================================

	private Utente utenteCorrente;

	// =====================================================
	// UTENTI
	// =====================================================

	private final List<Studente> studenti;

	private final List<Docente> docenti;

	private final List<ResponsabileOrari> responsabili;

	// =====================================================
	// COSTRUTTORE
	// =====================================================

	public Controller(
			Orario orario,
			List<Studente> studenti,
			List<Docente> docenti,
			List<ResponsabileOrari> responsabili
	) {

		this.orario = orario;

		this.studenti = studenti;

		this.docenti = docenti;

		this.responsabili = responsabili;
	}

	// =====================================================
	// LOGIN
	// =====================================================

	public boolean login(
			String login,
			String password
	) {

		for (ResponsabileOrari r : responsabili) {

			if (r.login(login, password)) {

				utenteCorrente = r;

				return true;
			}
		}

		for (Docente d : docenti) {

			if (d.login(login, password)) {

				utenteCorrente = d;

				return true;
			}
		}

		for (Studente s : studenti) {

			if (s.login(login, password)) {

				utenteCorrente = s;

				return true;
			}
		}

		return false;
	}

	// =====================================================
	// LOGOUT
	// =====================================================

	public void logout() {

		utenteCorrente = null;
	}

	// =====================================================
	// GETTER SESSIONE
	// =====================================================

	public Utente getUtenteCorrente() {

		return utenteCorrente;
	}

	public boolean isLogged() {

		return utenteCorrente != null;
	}

	public String getNomeUtenteCorrente() {

		if (utenteCorrente == null) {

			return "";
		}

		return utenteCorrente.getNome()
				+ " "
				+ utenteCorrente.getCognome();
	}

	// =====================================================
	// RUOLI
	// =====================================================

	public boolean isStudente() {

		return utenteCorrente instanceof Studente;
	}

	// ResponsabileOrari è anche Docente

	public boolean isDocente() {

		return utenteCorrente instanceof Docente;
	}

	public boolean isResponsabile() {

		return utenteCorrente
				instanceof ResponsabileOrari;
	}

	// =====================================================
	// AULE
	// =====================================================

	public void aggiungiAula(
			String nomeAula
	) {

		Aula aula =
				new Aula(nomeAula);

		orario.aggiungiAula(aula);
	}

	public List<Aula> getAule() {

		return orario.getAule();
	}

	public Aula cercaAula(
			String nomeAula
	) {

		for (Aula aula : orario.getAule()) {

			if (aula.getNomeAula()
					.equalsIgnoreCase(nomeAula)) {

				return aula;
			}
		}

		return null;
	}

	// =====================================================
	// INSEGNAMENTI
	// =====================================================

	public void aggiungiInsegnamento(
			String nome,
			int cfu,
			AnnoCorso annoCorso,
			Docente docenteTitolare
	) {

		Insegnamento insegnamento =
				new Insegnamento(
						nome,
						cfu,
						annoCorso,
						docenteTitolare
				);

		orario.aggiungiInsegnamento(
				insegnamento
		);
	}

	public List<Insegnamento>
	getInsegnamenti() {

		return orario.getInsegnamenti();
	}

	public Insegnamento cercaInsegnamento(
			String nome
	) {

		for (Insegnamento i
				: orario.getInsegnamenti()) {

			if (i.getNomeInsegnamento()
					.equalsIgnoreCase(nome)) {

				return i;
			}
		}

		return null;
	}

	// =====================================================
	// CREAZIONE LEZIONE
	// =====================================================

	public boolean creaLezione(
			Insegnamento insegnamento,
			Giorno giorno,
			LocalTime oraInizio,
			LocalTime oraFine,
			Aula aula
	) {

		if (insegnamento == null
				|| giorno == null
				|| oraInizio == null
				|| oraFine == null
				|| aula == null) {

			return false;
		}

		// Controllo conflitto aula

		if (checkConflittoAula(
				giorno,
				oraInizio,
				oraFine,
				aula
		)) {

			return false;
		}

		// Controllo conflitto docente

		if (checkConflittoDocente(
				insegnamento,
				giorno,
				oraInizio,
				oraFine
		)) {

			return false;
		}

		Lezione lezione =
				new Lezione(
						insegnamento,
						giorno,
						oraInizio,
						oraFine,
						aula
				);

		return orario.aggiungiLezione(
				lezione
		);
	}

	// =====================================================
	// ELIMINA LEZIONE
	// =====================================================

	public boolean eliminaLezione(
			Lezione lezione
	) {

		return orario.rimuoviLezione(
				lezione
		);
	}

	// =====================================================
	// GET LEZIONI
	// =====================================================

	public List<Lezione> getLezioni() {

		return orario.getLezioni();
	}

	// =====================================================
	// OVERLAP
	// =====================================================

	private boolean isOverlap(
			LocalTime inizio1,
			LocalTime fine1,
			LocalTime inizio2,
			LocalTime fine2
	) {

		return inizio1.isBefore(fine2)
				&&
				fine1.isAfter(inizio2);
	}

	// =====================================================
	// CONFLITTO AULA
	// =====================================================

	public boolean checkConflittoAula(
			Giorno giorno,
			LocalTime oraInizio,
			LocalTime oraFine,
			Aula aula
	) {

		for (Lezione l : orario.getLezioni()) {

			boolean stessoGiorno =
					l.getGiornoSettimana()
							== giorno;

			boolean stessaAula =
					l.getAula()
							.equals(aula);

			boolean overlap =
					isOverlap(
							oraInizio,
							oraFine,
							l.getOraInizio(),
							l.getOraFine()
					);

			if (stessoGiorno
					&&
					stessaAula
					&&
					overlap) {

				return true;
			}
		}

		return false;
	}

	// =====================================================
	// CONFLITTO DOCENTE
	// =====================================================

	public boolean checkConflittoDocente(
			Insegnamento insegnamento,
			Giorno giorno,
			LocalTime oraInizio,
			LocalTime oraFine
	) {

		Docente docenteNuovo =
				insegnamento
						.getDocenteTitolare();

		for (Lezione l : orario.getLezioni()) {

			boolean stessoGiorno =
					l.getGiornoSettimana()
							== giorno;

			boolean stessoDocente =
					l.getInsegnamento()
							.getDocenteTitolare()
							.equals(docenteNuovo);

			boolean overlap =
					isOverlap(
							oraInizio,
							oraFine,
							l.getOraInizio(),
							l.getOraFine()
					);

			if (stessoGiorno
					&&
					stessoDocente
					&&
					overlap) {

				return true;
			}
		}

		return false;
	}

	// =====================================================
	// RICHIESTE
	// =====================================================

	public void inviaRichiesta(
			String motivazione,
			Giorno giornoAttuale,
			LocalTime oraInizioAttuale,
			LocalTime oraFineAttuale,
			Giorno giornoProposto,
			LocalTime oraInizioProposta,
			LocalTime oraFineProposta
	) {

		if (!(utenteCorrente
				instanceof Docente)) {

			return;
		}

		Docente docente =
				(Docente) utenteCorrente;

		RichiestaSpostamento richiesta =
				docente.inviaRichiesta(
						motivazione,
						giornoAttuale,
						oraInizioAttuale,
						oraFineAttuale,
						giornoProposto,
						oraInizioProposta,
						oraFineProposta
				);

		if (richiesta != null) {

			richiesta.setDocente(docente);

			orario.aggiungiRichiesta(
					richiesta
			);
		}
	}

	public List<RichiestaSpostamento>
	getRichieste() {

		return orario.getRichieste();
	}

	// =====================================================
	// APPROVA RICHIESTA
	// =====================================================

	public boolean approvaRichiesta(
			RichiestaSpostamento richiesta
	) {

		if (!(utenteCorrente
				instanceof ResponsabileOrari)) {

			return false;
		}

		Lezione lezioneDaSpostare =
				null;

		// Ricerca lezione

		for (Lezione l
				: orario.getLezioni()) {

			boolean stessoGiorno =
					l.getGiornoSettimana()
							==
							richiesta
									.getGiornoAttuale();

			boolean stessaOra =
					l.getOraInizio()
							.equals(
									richiesta
											.getOraInizioAttuale()
							);

			boolean stessoDocente =
					l.getInsegnamento()
							.getDocenteTitolare()
							.equals(
									richiesta
											.getDocente()
							);

			if (stessoGiorno
					&&
					stessaOra
					&&
					stessoDocente) {

				lezioneDaSpostare = l;

				break;
			}
		}

		if (lezioneDaSpostare == null) {

			return false;
		}

		// =================================================
		// RIMOZIONE TEMPORANEA
		// =================================================

		orario.rimuoviLezione(
				lezioneDaSpostare
		);

		try {

			// Controllo conflitto aula

			boolean conflittoAula =
					checkConflittoAula(
							richiesta
									.getGiornoProposto(),

							richiesta
									.getOraInizioProposta(),

							richiesta
									.getOraFineProposta(),

							lezioneDaSpostare
									.getAula()
					);

			// Controllo conflitto docente

			boolean conflittoDocente =
					checkConflittoDocente(
							lezioneDaSpostare
									.getInsegnamento(),

							richiesta
									.getGiornoProposto(),

							richiesta
									.getOraInizioProposta(),

							richiesta
									.getOraFineProposta()
					);

			if (conflittoAula
					|| conflittoDocente) {

				return false;
			}

			// Aggiornamento lezione

			lezioneDaSpostare
					.setGiornoSettimana(
							richiesta
									.getGiornoProposto()
					);

			lezioneDaSpostare
					.setOraInizio(
							richiesta
									.getOraInizioProposta()
					);

			lezioneDaSpostare
					.setOraFine(
							richiesta
									.getOraFineProposta()
					);

			// Approvazione richiesta

			richiesta.setStato(
					StatoRichiesta.APPROVATA
			);

			return true;

		} finally {

			// =================================================
			// RIPRISTINO SICURO
			// =================================================

			orario.aggiungiLezione(
					lezioneDaSpostare
			);
		}
	}

	// =====================================================
	// RIFIUTA RICHIESTA
	// =====================================================

	public void rifiutaRichiesta(
			RichiestaSpostamento richiesta
	) {

		if (!(utenteCorrente
				instanceof ResponsabileOrari)) {

			return;
		}

		richiesta.setStato(
				StatoRichiesta.RIFIUTATA
		);
	}

	// =====================================================
	// ORARIO COMPLETO
	// =====================================================

	public Object[][] getDatiTabellaOrario() {

		return buildTabellaLezioni(
				orario.getLezioni()
		);
	}

	// =====================================================
	// ORARIO STUDENTE
	// =====================================================

	public Object[][]
	getDatiTabellaOrarioStudente() {

		if (!(utenteCorrente
				instanceof Studente)) {

			return null;
		}

		Studente studente =
				(Studente) utenteCorrente;

		List<Lezione> filtrate =
				new ArrayList<>();

		for (Lezione l
				: orario.getLezioni()) {

			if (l.getInsegnamento()
					.getAnnoCorso()
					==
					studente.getAnnoCorso()) {

				filtrate.add(l);
			}
		}

		return buildTabellaLezioni(
				filtrate
		);
	}

	// =====================================================
	// ORARIO DOCENTE
	// =====================================================

	public Object[][]
	getDatiTabellaOrarioDocente() {

		if (!(utenteCorrente
				instanceof Docente)) {

			return null;
		}

		Docente docente =
				(Docente) utenteCorrente;

		List<Lezione> filtrate =
				new ArrayList<>();

		for (Lezione l
				: orario.getLezioni()) {

			if (l.getInsegnamento()
					.getDocenteTitolare()
					.equals(docente)) {

				filtrate.add(l);
			}
		}

		return buildTabellaLezioni(
				filtrate
		);
	}

	// =====================================================
	// BUILD TABELLA LEZIONI
	// =====================================================

	private Object[][]
	buildTabellaLezioni(
			List<Lezione> lezioni
	) {

		Object[][] dati =
				new Object[
						lezioni.size()
						][6];

		for (int i = 0;
		     i < lezioni.size();
		     i++) {

			Lezione l =
					lezioni.get(i);

			dati[i][0] =
					l.getInsegnamento()
							.getNomeInsegnamento();

			dati[i][1] =
					l.getInsegnamento()
							.getDocenteTitolare();

			dati[i][2] =
					l.getGiornoSettimana();

			dati[i][3] =
					l.getOraInizio();

			dati[i][4] =
					l.getOraFine();

			dati[i][5] =
					l.getAula()
							.getNomeAula();
		}

		return dati;
	}

	// =====================================================
	// TABELLA RICHIESTE
	// =====================================================

	public Object[][]
	getDatiTabellaRichieste() {

		List<RichiestaSpostamento>
				richieste =
				orario.getRichieste();

		Object[][] dati =
				new Object[
						richieste.size()
						][6];

		for (int i = 0;
		     i < richieste.size();
		     i++) {

			RichiestaSpostamento r =
					richieste.get(i);

			dati[i][0] =
					r.getDocente();

			dati[i][1] =
					r.getMotivazione();

			dati[i][2] =
					r.getStato();

			dati[i][3] =
					r.getGiornoAttuale();

			dati[i][4] =
					r.getGiornoProposto();

			dati[i][5] =
					r.getDataRichiesta();
		}

		return dati;
	}
}