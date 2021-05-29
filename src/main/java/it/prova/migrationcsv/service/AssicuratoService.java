package it.prova.migrationcsv.service;

import java.sql.Connection;

import org.apache.commons.lang3.StringUtils;

import it.prova.migrationcsv.Constants;
import it.prova.migrationcsv.connection.MyConnection;
import it.prova.migrationcsv.dao.AssicuratoDAO;
import it.prova.migrationcsv.model.Assicurato;

public class AssicuratoService {
	
	 AssicuratoDAO assicuratodao= new AssicuratoDAO();
	
	public int inserisciNuovo(Assicurato input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEWSCHEMA)) {

			this.assicuratodao.setConnection(connection);
			
			if(validaAssicurati(input)) {
				result= assicuratodao.insert(input);
			}else {
				result= assicuratodao.insertNotProcessed(input);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}
	
	private static boolean validaAssicurati(Assicurato assicuratoItem) {
		
		if(StringUtils.isBlank(assicuratoItem.getNome()) || 
				StringUtils.isBlank(assicuratoItem.getCognome()) ||
				StringUtils.isBlank(assicuratoItem.getCodiceFiscale()) ||
				assicuratoItem.getCodiceFiscale().length()!=16 ||
				StringUtils.isBlank(assicuratoItem.getDataNascita()) ||
				assicuratoItem.getNuoviSinistri()==null || assicuratoItem.getNuoviSinistri()< 0 ) {
			
			return false;
		}
		return true;		
	}
	
	

}
