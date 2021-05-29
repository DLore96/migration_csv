package it.prova.migrationcsv.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class Assicurato {
	
	@CsvBindByName(column="id")
	private Long id;
	@CsvBindByName(column="nome")
	private String nome;
	@CsvBindByName(column = "cognome")
	private String cognome;
	
	@CsvBindByName(column = "data_nascita")
	private String dataNascitaFromCsv;
	
	
	@CsvBindByName(column = "sinistri")
	private Integer nuoviSinistri;
	@CsvBindByName(column = "codice_fiscale")
	private String codiceFiscale;
	
public Assicurato(){}
	
	public Assicurato(String nome, String cognome, String dataNascita, Integer sinistri, String codiceFiscale) {
		this.nome=nome;
		this.cognome=cognome;
		this.dataNascitaFromCsv=dataNascita;
		this.codiceFiscale=codiceFiscale;
		this.nuoviSinistri=sinistri;
	}
	
	public Assicurato(Long id, String nome, String cognome,String  dataNascita, Integer nuoviSinistri, String codiceFiscale ) {
		this.id=id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale=codiceFiscale;
		this.nuoviSinistri=nuoviSinistri;
		this.dataNascitaFromCsv=dataNascita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataNascita() {
		return dataNascitaFromCsv;
	}

	public void setDataNascita(String  dataNascita) {
		this.dataNascitaFromCsv = dataNascita;
	}

	public Integer getNuoviSinistri() {
		return nuoviSinistri;
	}

	public void setNuoviSinistri(Integer nuoviSinistri) {
		this.nuoviSinistri = nuoviSinistri;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String toString() {
		return "Assicurato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascitaFromCsv
				+ ", nuoviSinistri=" + nuoviSinistri + ", codiceFiscale=" + codiceFiscale + "]";
	}
	
	
	public Date parseDate(String data) throws Exception {
		 return new SimpleDateFormat("yyyy-MM-dd").parse(data); 
	}
	

}
