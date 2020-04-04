package it.polito.tdp.corsi.db;

import java.sql.*;
import java.util.*;
import javax.management.RuntimeErrorException;
import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List <Corso> getCorsiPeriodo (int pd) {
		
		String sql = "SELECT * FROM corso WHERE pd = ?;";
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			conn.close();		//ESSENZIALE
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	
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
}
