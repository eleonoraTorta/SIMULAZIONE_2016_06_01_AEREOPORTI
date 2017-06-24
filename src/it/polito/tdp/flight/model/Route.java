package it.polito.tdp.flight.model;

public class Route {
	
	private Airline compagnia;
	private Airport partenza;
	private Airport destinazione;
	
	
	public Route(Airline compagnia, Airport partenza, Airport destinazione) {
		super();
		this.compagnia = compagnia;
		this.partenza = partenza;
		this.destinazione = destinazione;
	}


	public Airline getCompagnia() {
		return compagnia;
	}


	public void setCompagnia(Airline compagnia) {
		this.compagnia = compagnia;
	}


	public Airport getPartenza() {
		return partenza;
	}


	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}


	public Airport getDestinazione() {
		return destinazione;
	}


	public void setDestinazione(Airport destinazione) {
		this.destinazione = destinazione;
	}
	
	
	
	
//	private String airline ;
//	private int airlineId ;
//	private String sourceAirport ;
//	private int sourceAirportId ;
//	private String destinationAirport ;
//	private int destinationAirportId ;
//	private String codeshare ;
//	private int stops ;
//	private String equipment ;
	
//	public Route(String airline, int airlineId, String sourceAirport, int sourceAirportId, String destinationAirport,
//			int destinationAirportId) {
//		super();
//		this.airline = airline;
//		this.airlineId = airlineId;
//		this.sourceAirport = sourceAirport;
//		this.sourceAirportId = sourceAirportId;
//		this.destinationAirport = destinationAirport;
//		this.destinationAirportId = destinationAirportId;
//		this.codeshare = codeshare;
//		this.stops = stops;
//		this.equipment = equipment;
//	}
	
//	public String getAirline() {
//		return airline;
//	}
//	
//	public void setAirline(String airline) {
//		this.airline = airline;
//	}
//	
//	public int getAirlineId() {
//		return airlineId;
//	}
//	
//	public void setAirlineId(int airlineId) {
//		this.airlineId = airlineId;
//	}
//	
//	public String getSourceAirport() {
//		return sourceAirport;
//	}
//	
//	public void setSourceAirport(String sourceAirport) {
//		this.sourceAirport = sourceAirport;
//	}
//	
//	public int getSourceAirportId() {
//		return sourceAirportId;
//	}
//	
//	public void setSourceAirportId(int sourceAirportId) {
//		this.sourceAirportId = sourceAirportId;
//	}
//	
//	public String getDestinationAirport() {
//		return destinationAirport;
//	}
//	
//	public void setDestinationAirport(String destinationAirport) {
//		this.destinationAirport = destinationAirport;
//	}
//	
//	public int getDestinationAirportId() {
//		return destinationAirportId;
//	}
//	
//	public void setDestinationAirportId(int destinationAirportId) {
//		this.destinationAirportId = destinationAirportId;
//	}
//	
//	public String getCodeshare() {
//		return codeshare;
//	}
//	
//	public void setCodeshare(String codeshare) {
//		this.codeshare = codeshare;
//	}
//	
//	public int getStops() {
//		return stops;
//	}
//	
//	public void setStops(int stops) {
//		this.stops = stops;
//	}
//	
//	public String getEquipment() {
//		return equipment;
//	}
//	
//	public void setEquipment(String equipment) {
//		this.equipment = equipment;
//	}

}