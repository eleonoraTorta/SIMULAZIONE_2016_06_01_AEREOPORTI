package it.polito.tdp.flight;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.AirportDistance;
import it.polito.tdp.flight.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FlightController {

	Model model ;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Airline> boxAirline;

    @FXML
    private ComboBox<Airport> boxAirport;

    @FXML
    private TextArea txtResult;

    
    @FXML
    void doServiti(ActionEvent event) {
    	
    	txtResult.clear();
    	Airline a = boxAirline.getValue();
    	if( a == null){
    		txtResult.appendText("ERRORE: selezionare una compagnia.\n");
    		return;
    	}
    	model.creaGrafo(a);
    	List <Airport> raggiungibili = model.getAereoportiRaggiungibili();
    	txtResult.appendText("Elenco degli aereoporti raggiungibili dalla compagnia aerea " + a.getName() + ":\n");
    	for( Airport aereo : raggiungibili){
    		txtResult.appendText(aereo.getName() + "\n");
    	}
    	
    	boxAirport.getItems().addAll(raggiungibili);

    }
    
    @FXML
    void doRaggiungibili(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Airline a = boxAirline.getValue();
    	if( a == null){
    		txtResult.appendText("ERRORE: selezionare una compagnia.\n");
    		return;
    	}
    	model.creaGrafo(a);
    	
    	Airport partenza = boxAirport.getValue();
    	if( partenza == null){
    		txtResult.appendText("ERRORE: selezionare un aereo da cui far partire la ricerca.\n");
    		return;
    	}
    	
    	List <AirportDistance> mete = model.getAereoportiRaggiungibiliFromAirport(partenza);
    	txtResult.appendText("Elenco degli aereporti raggiungibli dall'aereoporto di " + partenza.getName()+ " tramite la compagnia aerea " + a.getName() + "\n");
    	for( AirportDistance aereo : mete){
    		txtResult.appendText(aereo.getDestinazione().getName() + ": " + aereo.getDistanza() + " KM, " + aereo.getScali() + " scali\n");
    	}
    	
    }

    

    @FXML
    void initialize() {
        assert boxAirline != null : "fx:id=\"boxAirline\" was not injected: check your FXML file 'Flight.fxml'.";
        assert boxAirport != null : "fx:id=\"boxAirport\" was not injected: check your FXML file 'Flight.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Flight.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
		boxAirline.getItems().addAll(model.getAirlines());
		
	}
}
