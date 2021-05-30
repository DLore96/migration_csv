package it.prova.migrationcsv;

import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import it.prova.migrationcsv.model.Assicurato;
import it.prova.migrationcsv.service.AssicuratoService;

public class Test {

	public static void main(String[] args) throws Exception {

		System.out.println("avvio lettura file:");
		String nomeFile="C:\\Users\\asus\\Desktop\\file csv\\prova.csv";
		AssicuratoService assicuratoService= new AssicuratoService();
		//ORA COSTRUISCO ATTRAVERSO CsvToBeanBuilder un oggetto opportunamente annotato, utilizzando i dati presi 
		//dal file prova.csv 
		List<Assicurato> assicurati = new CsvToBeanBuilder(new FileReader(nomeFile))
				.withType(Assicurato.class)
				.build()
				.parse();
		System.out.println("Ora inseriamoli nella base dati:");
		for(Assicurato app: assicurati) {			 
			 //INSERIMENTO IN TABELLA ASSICURATO:
			 
			System.out.println("Sto processando:"+ app.toString());
			
			 assicuratoService.inserisciNuovo(app);
			 
		}
		System.out.println("ecco i nuovi assicurati");
		 
		 assicuratoService.ListAll().forEach(x -> System.out.println(x.toString()));
		
		
	}

}
