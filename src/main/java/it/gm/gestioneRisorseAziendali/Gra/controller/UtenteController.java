package it.gm.gestioneRisorseAziendali.Gra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.gm.gestioneRisorseAziendali.Gra.entity.Dipendente;
import it.gm.gestioneRisorseAziendali.Gra.entity.ProfiloUtente;
import it.gm.gestioneRisorseAziendali.Gra.entity.Utente;
import it.gm.gestioneRisorseAziendali.Gra.service.UtenteInterface;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteInterface serviceUtente;

	@PostMapping(value = "/doSignIn")
	public @ResponseBody Utente createNewUtente(@RequestBody Utente utente) {

		System.out.println("pre registrazione " + utente.toString());

		ProfiloUtente pU = new ProfiloUtente();
		pU.setIdProfiloUtente(2);
		utente.setProfiloUtente(pU);
		Utente utenteSalvato = null;
		utenteSalvato = serviceUtente.saveUtente(utente);
		System.out.println("post registrazione " + utenteSalvato.toString());

		return utenteSalvato;

	}

	@PostMapping(value = "/doLogIn")
	public @ResponseBody Utente checkUtente(@RequestBody Utente utente) {
		Utente utenteTrovato = null;
		
		utenteTrovato = serviceUtente.checkUtente(utente);

		System.out.println("Utente esistente nel Db "+utenteTrovato);
		return utenteTrovato;

	}

	@GetMapping(value = "/doListUtenti")
	public @ResponseBody List<Utente> UtenteList() {

		List<Utente> listaUtenti = serviceUtente.getAllUtente();

		return listaUtenti;
	}
	

	@DeleteMapping(value = "/deleteUtente/{idUtente}")
	public void deleteById(@PathVariable Integer idUtente) {

		serviceUtente.deleteUtente(idUtente);

		System.out.println("Utente  cancellato");

	}
	
	@PostMapping(value = "/utenteEmail/{email}")
	public @ResponseBody Utente recuperaUtEmail(@PathVariable String email) {

		Utente utenteChecked = serviceUtente.getByEmail(email);
		System.out.println("Utente recuperato con email ;)) " + utenteChecked);

		return utenteChecked;

	}

	@GetMapping(value = "/recuperaUtente/{idUtente}")
	public @ResponseBody Utente recuperaCliente(@PathVariable Integer idUtente) {

		Utente utenteChecked = serviceUtente.recuperaUtente(idUtente);
		// System.out.println(user);

		return utenteChecked;

	}

	@PutMapping(value = "/updateUtente/{idUtente}")
	public @ResponseBody Utente updateUtente(@PathVariable Integer idUtente, @RequestBody Utente utente) {
		Utente utenteNotUpdated = serviceUtente.recuperaUtente(idUtente);
		
		ProfiloUtente pF = new ProfiloUtente();
		pF.setIdProfiloUtente(2);
		utente.setProfiloUtente(pF);
		
		Utente utenteUpdated = serviceUtente.updateUtente(utente);
		
		
		System.out.println("Utente AGGIORNATO " + utenteUpdated);

		return utenteUpdated;

	}
	
	@PutMapping(value = "/abilita_disabilitaUtente/{idUtente}")
	public @ResponseBody Utente abilitaUtente(@PathVariable Integer idUtente) {
//		ProfiloUtente pF = new ProfiloUtente();
//		pF.setIdProfiloUtente(2);
//		utente.setProfiloUtente(pF);
		Utente utenteChecked = serviceUtente.recuperaUtente(idUtente);
		System.out.println("Utente recuperato " + utenteChecked);
		
			Utente utenteUpdated = serviceUtente.updateUtente(utenteChecked);
			System.out.println("Utente DISABILITATO " + utenteUpdated);
			return utenteUpdated;
		
		}
		

	


	@PostMapping(value = "/searchUtente")
	public @ResponseBody List<Utente> search(@RequestBody Utente utente) {
		List<Utente> risultatiRicerca = new ArrayList<>();
		System.out.println("utente arrivato dal fron end " + utente.toString());
		if (!utente.getNomeUtente().equals("") && utente.getEmail().equals("")  ) {
			risultatiRicerca = serviceUtente.searchByNomeUtente(utente.getNomeUtente());
			System.out.println(risultatiRicerca.toString());
			return risultatiRicerca;
		} else if (utente.getNomeUtente().equals("") && !utente.getEmail().equals("") ) {
			risultatiRicerca = serviceUtente.searchByEmail(utente.getEmail());
			System.out.println(risultatiRicerca.toString());
			return risultatiRicerca;
		} else if (utente.getNomeUtente().equals("") && utente.getEmail().equals("")  ) {
			risultatiRicerca = serviceUtente.searchByNomeUtenteAndEmail(utente.getNomeUtente(), utente.getEmail());
			System.out.println(risultatiRicerca.toString());
			return risultatiRicerca;
		}
		
		return risultatiRicerca;

	}

}
