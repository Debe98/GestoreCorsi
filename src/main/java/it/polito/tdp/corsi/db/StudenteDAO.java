package it.polito.tdp.corsi.db;

import java.sql.*;
import java.util.*;
import javax.management.RuntimeErrorException;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

	public List <Studente> getStudentiCorso (String codins) {
		
		String sql = "SELECT studente.matricola, cognome, nome, cds\r\n" + 
				"FROM studente, iscrizione\r\n" + 
				"WHERE studente.matricola = iscrizione.matricola AND codins = ?";
		List<Studente> result = new ArrayList<>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setNString(1, codins);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
				result.add(s);
			}
			
			conn.close();		//ESSENZIALE
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	/*
	public Map <Corso, Integer> getNumStudentiCorsiPeriodo (int pd) {
		
		String sql = "SELECT corso.codins, pd, nome, crediti, COUNT(*) AS CNT\r\n" + 
				"FROM corso, iscrizione\r\n" + 
				"WHERE corso.codins = iscrizione.codins AND pd = ?\r\n" + 
				"GROUP BY corso.codins, pd, nome, crediti;";
		Map<Corso, Integer> result = new HashMap<>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.put(c, rs.getInt("CNT"));
			}
			
			conn.close();		//ESSENZIALE
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	*/
}
