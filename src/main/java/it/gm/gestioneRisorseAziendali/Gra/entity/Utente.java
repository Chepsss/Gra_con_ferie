package it.gm.gestioneRisorseAziendali.Gra.entity;

import java.io.Serializable;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "utente")
public class Utente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 765456L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUtente;

	@NotNull
	@Column(name = "nomeUtente")
	private String nomeUtente;

	@NotNull
	@Column(name = "email", unique = true)
	private String email;

	@NotNull
	@Column(name = "password")
	private String password;

	

	@NotNull
	@JoinColumn(name = "profiloutente")
	@ManyToOne
	private ProfiloUtente profiloUtente;

	@NotNull
	@JoinColumn(name = "societacollaborazioneFk")
	@ManyToOne
	private SocietaCollaborazione societaCollaborazione;

	@Override
	public int hashCode() {
		return Objects.hash(email, idUtente, nomeUtente, password, profiloUtente, societaCollaborazione);
	}



	public SocietaCollaborazione getSocietaCollaborazione() {
		return societaCollaborazione;
	}

	public void setSocietaCollaborazione(SocietaCollaborazione societaCollaborazione) {
		this.societaCollaborazione = societaCollaborazione;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public ProfiloUtente getProfiloUtente() {
		return profiloUtente;
	}

	public void setProfiloUtente(ProfiloUtente profiloUtente) {
		this.profiloUtente = profiloUtente;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(email, other.email) && Objects.equals(idUtente, other.idUtente)
				&& Objects.equals(nomeUtente, other.nomeUtente) && Objects.equals(password, other.password)
				&& Objects.equals(profiloUtente, other.profiloUtente)
				&& Objects.equals(societaCollaborazione, other.societaCollaborazione);
	}



	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nomeUtente=" + nomeUtente + ", email=" + email + ", password="
				+ password + ", profiloUtente=" + profiloUtente + ", societaCollaborazione=" + societaCollaborazione
				+ "]";
	}

	

}
