package it.prova.migrationcsv.model;

public class NotProcessedAssicurato {
	
	private Long id;
	private String codiceFiscale;
	private Long oldId;
	
	public NotProcessedAssicurato() {}
	
	public NotProcessedAssicurato(String codiceFiscale, Long oldId) {
		this.codiceFiscale=codiceFiscale;
		this.oldId=oldId;
	}
	
	public NotProcessedAssicurato(Long id, String codiceFiscale, Long oldId) {
		this.id=id;
		this.codiceFiscale=codiceFiscale;
		this.oldId=oldId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Long getOldId() {
		return oldId;
	}

	public void setOldId(Long oldId) {
		this.oldId = oldId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{id=").append(id).append(", codice_fiscale=").append(codiceFiscale).append(", old_id=")
				.append(oldId).append("}");

		return builder.toString();
	}
	
	

}
