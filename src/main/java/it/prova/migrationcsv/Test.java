package it.prova.migrationcsv;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import it.prova.migrationcsv.model.Assicurato;
import it.prova.migrationcsv.service.AssicuratoService;

public class Test {

	public static void main(String[] args) throws Exception {

		System.out.println("avvio lettura file:");
		String nomeFile="C:\\Users\\asus\\Desktop\\file csv\\prova.csv";
		List<Assicurato> assicurati = new CsvToBeanBuilder(new CSVReader(new FileReader(nomeFile)))
				.withType(Assicurato.class)
				.build()
				.parse();
		System.out.println("ok, ora stampo il risultato:");
		for(Assicurato app: assicurati) {
			System.out.println(app.toString());
			System.out.println("stampo la data converita: ");
			 Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(app.getDataNascita()); 
			 System.out.println(date1);
			 
			 
			 //INSERIMENTO IN TABELLA ASSICURATO:
			 System.out.println("Ora inseriamolo nella base dati:");
			 
			 AssicuratoService assicuratoService= new AssicuratoService();
			 assicuratoService.inserisciNuovo(app);
				/*try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEWSCHEMA);
						PreparedStatement st = connection.prepareStatement(
						"INSERT INTO assicurato(id, nome, cognome, datanascita,sinistri,codice_fiscale) VALUES (?, ?, ?, ?, ?, ?);")) {
					st.setLong(1, app.getId());
					st.setString(2, app.getNome());
					st.setString(3, app.getCognome());
					st.setDate(4, new java.sql.Date(app.parseDate(app.getDataNascita()).getTime()));
					st.setInt(5, app.getNuoviSinistri());
					st.setString(6, app.getCodiceFiscale());
					st.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					
				}*/
		}
		
		
	}

}
