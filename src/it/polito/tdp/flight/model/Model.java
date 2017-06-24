package it.polito.tdp.flight.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flight.db.FlightDAO;

public class Model {
	
	private FlightDAO dao ;
	private List <Airline> compagnie;
	private List <Airport> aeroporti;
	private List <Route> tratte;
	private List <Airport> raggiungibili;
	private Map <Integer, Airport> mappaAereoporti;
	private SimpleDirectedWeightedGraph <Airport, DefaultWeightedEdge> grafo ; 
	
	

	public Model() {
		super();
		this.dao = new FlightDAO();
		this.mappaAereoporti = new TreeMap <Integer, Airport>();
	}



	public List <Airline>  getAirlines() {
		if( compagnie == null){
			compagnie = dao.getAllAirlines();
		}
		return compagnie;
	}
	
	public List <Airport> getTuttiAereoporti(){
		if( aeroporti == null){
			aeroporti = dao.getAllAirports();
		
			for(Airport a : aeroporti ){
				mappaAereoporti.put(a.getAirportId(),a);
			}
		}
		return aeroporti;
	}

	public List <Route> getRoutes(Airline compagnia){
		if(tratte == null ){
			tratte = dao.getRoutesOfAirline(compagnia, mappaAereoporti);
		}
		return tratte;
		
	}


	public void creaGrafo(Airline compagnia) {
		this.grafo = new SimpleDirectedWeightedGraph <Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// Aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.getTuttiAereoporti());
		
		// Aggiungo gli archi
		for( Route tratta : this.getRoutes(compagnia)){
			double distanza = this.calcolaDistanza(tratta.getPartenza(), tratta.getDestinazione());
			Graphs.addEdge(this.grafo, tratta.getPartenza(), tratta.getDestinazione(), distanza);
		}
		
	//	System.out.println(this.grafo);
		
		
	}
	
	public List <Airport> getAereoportiRaggiungibili(){
		raggiungibili = new ArrayList <Airport>();
		for( Airport a : this.grafo.vertexSet()){
			if( Graphs.neighborListOf(this.grafo, a).size() != 0){
				raggiungibili.add(a);
			}
		}
		Collections.sort(raggiungibili);
		
		return raggiungibili;
	}
	
	public List <Airport> getRaggiungibili(){
		if( raggiungibili == null){
			raggiungibili = this.getAereoportiRaggiungibili();
		}
		return raggiungibili;
	}



	public double calcolaDistanza( Airport a, Airport b){
		LatLng partenza = new LatLng (a.getLatitude(), a.getLongitude());
		LatLng arrivo = new LatLng (b.getLatitude(), b.getLongitude());
		
		double distanza = LatLngTool.distance(partenza, arrivo, LengthUnit.KILOMETER);
		return distanza;
	}



	public List<AirportDistance> getAereoportiRaggiungibiliFromAirport(Airport partenza) {
		List <AirportDistance> destinazioni = new ArrayList <AirportDistance>();
		
		DijkstraShortestPath <Airport, DefaultWeightedEdge> dsp ;
		
		for ( Airport a : this.getRaggiungibili()){
			if(! a.equals(partenza)){
			
				dsp =  new DijkstraShortestPath <Airport, DefaultWeightedEdge>(this.grafo, partenza, a);
				GraphPath<Airport, DefaultWeightedEdge> path = dsp.getPath();
			
				if( path != null){
				
					double distanza = path.getWeight();
					AirportDistance  meta  = new AirportDistance(partenza, a, distanza, dsp.getPathEdgeList().size());
					destinazioni.add(meta);
				}
			}
		}
		
		Collections.sort(destinazioni);
		return destinazioni;
	}

}
