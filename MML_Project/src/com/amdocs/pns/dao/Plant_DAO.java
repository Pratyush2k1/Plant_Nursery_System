package com.amdocs.pns.dao;

import com.amdocs.pns.model.Plant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;
import com.amdocs.pns.exception.*;

public class Plant_DAO {
	
	// Connecting the database and creating the connection object
	DB_conn dbc = new DB_conn();
	Connection conn = dbc.getconnection();
	
	// Method to add new plants
	public int addPlant(Plant p) throws ClassNotFoundException {
		 
		String insertQuery = "INSERT INTO plant (plantId, plantName, originCountryName, sunlightRequired, waterSupplyFrequency, plantType, cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pst = conn.prepareStatement(insertQuery)) {
			pst.setInt(1, p.getPlantId());
		    pst.setString(2, p.getPlantName());
		    pst.setString(3, p.getOriginCountryName());
		    pst.setInt(4, p.getSunLightRequired());
		    pst.setString(5, p.getWaterSupplyFrequency());
		    pst.setString(6, p.getPlantType());
		    pst.setDouble(7, p.getCost());
		    
		    int rowsInserted = pst.executeUpdate();
		    
		    if (rowsInserted > 0) {
		    	System.out.println("---------- Plant record inserted successfully ----------");
		    	System.out.println();
		   } else {
		        System.out.println("---------- Failed to insert plant record ----------");
		        }
		    } catch (SQLException e) {
		    	System.out.println("Error: Enter Valid Input");
		    	System.out.println();
		    	}
		return 0;
	}
	
	// Method to delete Plants Whenever Required
	public boolean deletePlant(int pid) {
		
		try {
			Statement stmt=conn.createStatement();
			String selectquery="select * from plant where plantId='"+pid+"'";
			ResultSet rs=stmt.executeQuery(selectquery);
			String query2;
			
			if(rs.next()) {
			query2="delete from plant where plantId='"+pid+"'";
			stmt.executeUpdate(query2);
			return true;
			} 
			else {
				System.out.println("----------Plant not found----------");
				return false;
				}
			} catch(SQLException e) {
				e.printStackTrace();
				}
		return true;
		}
	
	
	// Method to update Plant cost with given Plant Id
	public boolean updatePlantCost(int plid,double newCost) {
		
		try {
			Statement stmt=conn.createStatement();
			String updatequery = "UPDATE plant SET cost=" + newCost + " WHERE plantId='" + plid + "'";
			int rows=stmt.executeUpdate(updatequery);
			
			 if(rows>0)
				return true;
				return false;
				} catch(SQLException e) {
					e.printStackTrace();
				}
		return true;
		}

	
	// Method to show all Plants
	public ArrayList<Plant> showAllPlants() {
		ArrayList<Plant> arr1=new ArrayList<Plant>();
		
		try {
			String query="select * from plant";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			       while (rs.next()) {
			    	   int id=rs.getInt("plantId");
				       String pname=rs.getString("plantName");
				       String cname=rs.getString("originCountryName");
				       int sun=rs.getInt("sunlightRequired");
				       String water=rs.getString("waterSupplyFrequency");
				       String ptype=rs.getString("plantType");
				       double cost=rs.getDouble("cost");
				       Plant p1=new Plant(id,pname,ptype,cname,water,cost,sun);
				       arr1.add(p1);
			       }  
			} catch(SQLException e) {
				e.printStackTrace();
			}
			        return arr1;
			}
	

	// Method to search plant name by its Origin Country
	public ArrayList<Plant> searchByOriginCountryName(String cntry) throws WrongALException{
		ArrayList<Plant> arr=new ArrayList<Plant>();
		try {
		Statement stmt=conn.createStatement();
		String query1="select * from plant where originCountryName='"+cntry+"'";
		ResultSet rs = stmt.executeQuery(query1);
		while (rs.next()) {
		        int id=rs.getInt("plantId");
		        String pname=rs.getString("plantName");
		        String cname=rs.getString("originCountryName");
		        String cname1 = cname.toLowerCase();
		        int sun=rs.getInt("sunlightRequired");
		        String water=rs.getString("waterSupplyFrequency");
		        String ptype=rs.getString("plantType");
		        double cost=rs.getDouble("cost");
		        Plant p=new Plant(id,pname,ptype,cname1,water,cost,sun);
		        arr.add(p);
		        }
		
		if(arr.size() == 0) {
			throw new WrongALException("This Parameter does not exist!!!!!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		    return arr;
		}

	
	// Method to show Outdoor plants with sunlight
	public ArrayList<Plant> searchOutdoorPlantsWithSunlight(){
		ArrayList<Plant> arr=new ArrayList<Plant>();
		try {
			Statement stmt=conn.createStatement();
			String query1="select * from plant where plantType='outdoor' and sunlightRequired = 1";
			ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
				int id=rs.getInt("plantId");
				String pname=rs.getString("plantName");
		        String cname=rs.getString("originCountryName");
		        int sun=rs.getInt("sunlightRequired");
		        String water=rs.getString("waterSupplyFrequency");
		        String ptype=rs.getString("plantType");
		        double cost=rs.getDouble("cost");
		        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
		           arr.add(p);
		        }
				} catch(SQLException e) {
					e.printStackTrace();
				}
        return arr;
		}

	
	// Method to show Water Supply Frequency to the Plants
	public int countPlantsByWaterSupplyFrequency(String waterSupplyFrequency) {
		String query = "SELECT COUNT(*) FROM plant WHERE waterSupplyFrequency = '" + waterSupplyFrequency + "'";
        try (Statement statement = conn.createStatement()) {
        	ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
            	try {
            		if(resultSet.getInt(1) == 0) {
            			throw new WrongFreqException("This term does not exist....Please try again!!! ");
            		} else {
            			return resultSet.getInt(1);
            		}
            	} catch (WrongFreqException e) {
        			System.out.println(e);
            	}   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
}
}
        

