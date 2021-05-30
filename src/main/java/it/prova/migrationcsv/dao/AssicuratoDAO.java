package it.prova.migrationcsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				"INSERT INTO assicurato( nome, cognome, datanascita,sinistri,codice_fiscale) VALUES (?, ?, ?, ?, ?);")) {
			st.setString(1, input.getNome());
			st.setString(2, input.getCognome());
			st.setDate(3, new java.sql.Date(input.parseDate(input.getDataNascita()).getTime()));
			st.setInt(4, input.getNuoviSinistri());
			st.setString(5, input.getCodiceFiscale());
			result = st.executeUpdate();
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
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}
	
	public List<Assicurato> listAll()throws Exception{
		List<Assicurato> result = new ArrayList<Assicurato>();
		Assicurato holderTemp = null;
		try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("select * from assicurato; ");){
			
			while (rs.next()) {
				holderTemp = new Assicurato();
				holderTemp.setId(rs.getLong("id"));
				holderTemp.setNome(rs.getString("nome"));
				holderTemp.setCognome(rs.getString("cognome"));
				holderTemp.setDataNascita(rs.getDate("datanascita").toString());
				holderTemp.setCodiceFiscale(rs.getString("codice_fiscale"));
				holderTemp.setNuoviSinistri(rs.getInt("sinistri"));
				result.add(holderTemp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
