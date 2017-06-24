package it.polito.tdp.flight.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class AirportDistance implements Comparable <AirportDistance> {
	
	private Airport partenza;
	private Airport destinazione;
	private double distanza;
	private int scali;
	
	public AirportDistance(Airport partenza, Airport destinazione, double distanza, int scali) {
		super();
		this.partenza = partenza;
		this.destinazione = destinazione;
		this.distanza = distanza;
		this.scali = scali;
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

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}

	public int getScali() {
		return scali;
	}

	public void setScali(int scali) {
		this.scali = scali;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinazione == null) ? 0 : destinazione.hashCode());
		result = prime * result + ((partenza == null) ? 0 : partenza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirportDistance other = (AirportDistance) obj;
		if (destinazione == null) {
			if (other.destinazione != null)
				return false;
		} else if (!destinazione.equals(other.destinazione))
			return false;
		if (partenza == null) {
			if (other.partenza != null)
				return false;
		} else if (!partenza.equals(other.partenza))
			return false;
		return true;
	}
	
	
	public double calcolaDistanza( Airport a, Airport b){
		LatLng partenza = new LatLng (a.getLatitude(), a.getLongitude());
		LatLng arrivo = new LatLng (b.getLatitude(), b.getLongitude());
		
		double distanza = LatLngTool.distance(partenza, arrivo, LengthUnit.KILOMETER);
		return distanza;
	}

	@Override
	public int compareTo(AirportDistance altra) {
		if( this.distanza > altra.distanza){
			return 1;
		}
		
		if( this.distanza< altra.distanza){
			return -1;
		}
	
		return 0;
	}
	
	
	

}
