package it.prova.migrationcsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import it.prova.migrationcsv.model.Assicurato;

public class AssicuratoDAO extends AbstractMySQLDAO{
	
	public void setConnection(Connection connection) {
		this.connection= connection;
	}
	
	public int insert(Assicurato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		int result=0;
		try (PreparedStatement st = connection.prepareStatement(
				"INSERT INTO assicurato(id, nome, cognome, datanascita,sinistri,codice_fiscale) VALUES (?, ?, ?, ?, ?, ?);")) {
			st.setLong(1, input.getId());
			st.setString(2, input.getNome());
			st.setString(3, input.getCognome());
			st.setDate(4, new java.sql.Date(input.parseDate(input.getDataNascita()).getTime()));
			st.setInt(5, input.getNuoviSinistri());
			st.setString(6, input.getCodiceFiscale());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
		
	}
	
	public int insertNotProcessed(Assicurato input) throws Exception{
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		int result=0;
		try (PreparedStatement st = connection.prepareStatement(
				"INSERT INTO not_processed(codice_fiscale, old_id) VALUES (?, ?);")) {				
			st.setString(1, input.getCodiceFiscale());
			st.setLong(2, input.getId());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}


}
