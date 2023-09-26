package com.amdocs.pns.main;

import java.util.ArrayList;
import java.util.Scanner;
import com.amdocs.pns.model.*;
import com.amdocs.pns.dao.*;
import com.amdocs.pns.exception.*;

public class Plant_Nursery_System {
	public static void main(String args[]) {
		Plant_DAO pdao = new Plant_DAO();
		Scanner sc=new Scanner(System.in);
		int ch = 0;
		
		try {
			do {
		        System.out.println("Press 1 to add new plants in the nursery System");
		        System.out.println("Press 2 to update the cost of the plant");
		        System.out.println("Press 3 to delete the plant details");
		        System.out.println("Press 4 to view all plants in the nursery System");
		        System.out.println("Press 5 to find the plant details by its origin country name");
		        System.out.println("Press 6 to find the outdoor plants which requires sunlight");
		        System.out.println("Press 7 to count number of plants by water supply frequency");
		        System.out.println("Press 8 to exit the system");
		        System.out.println("Please enter your choice");
		        try { ch=sc.nextInt();
		        
		        switch(ch) {
		        case 1:
			        System.out.println("--------- Enter all the details of the Plant ----------");
			        System.out.print("Enter Plant ID : ");
			        int id = sc.nextInt();
			        sc.nextLine();
			        System.out.print("Enter Plant Name : ");
			        String pname = sc.nextLine();
			        System.out.print("Enter Plant Origin Country Name : ");
			        String cname = sc.nextLine();
			        System.out.print("Enter if Plant Required Sunlight (1 for Yes, 0 for No) : ");
			        int sunreq = sc.nextInt();
			        sc.nextLine(); 
			        System.out.print("Enter Plant Water Supply Frequency : ");
			        String wsfreq = sc.nextLine();
			        System.out.print("Enter Plant Type (indoor/outdoor) : ");
			        String ptype = sc.nextLine();
			        System.out.print("Enter the Price of the Plant : ");
			        double cost = sc.nextDouble();
			        sc.nextLine(); 
			        Plant p=new Plant(id,pname,ptype,cname,wsfreq,cost,sunreq);
			        pdao.addPlant(p);
		        break;
		        
		        case 2:
		        	System.out.println("---------- Enter the Plant ID for Updation ----------");
			        int puid=sc.nextInt();
			        System.out.println("Enter the updated cost");
			        double ucost=sc.nextDouble();
			        sc.nextLine();
			        boolean b=pdao.updatePlantCost(puid, ucost);
			        if( b == true)
			        	System.out.println("Plant cost has been updated");
			        else
			        	System.out.println("Plant cost did not update");
			        //System.out.println(b);
			        System.out.println();
		        break;
		        
		        case 3:
		        	try {
				        System.out.println("---------- Enter the Plant ID for Deletion ----------");
				        int pdid=sc.nextInt();
				        if(pdid < 0) {
				        	try {
				        		throw new WrongIDException("Invalid ID, Enter a valid ID");
				        		} catch(WrongIDException e) {
				        			System.out.println(e);
				        			sc.nextLine();
				        			break;
				        			}
				        		}
				        pdao.deletePlant(pdid);
				        System.out.println("Plant with id " + pdid + " has been deleted successfully!!!");
				        System.out.println();
				        } catch(Exception e) {
				        	e.printStackTrace();
				        }
		        break;
		        
		        case 4:
		        	ArrayList<Plant> arr = pdao.showAllPlants();
			        for(int i=0;i<arr.size();i++) {
			        	Plant t=arr.get(i);
				        System.out.println("---------------------");
				        System.out.println("ID : "+ t.plantId+" ");
				        System.out.println("Plant Name : "+ t.plantName+" ");
				        System.out.println("Origin Country Name : "+ t.originCountryName+" ");
				        System.out.println("Plant Type : "+ t.plantType+" ");
				        System.out.println("Cost : "+ t.cost);
				       }
			        System.out.println();
			    break;
		        
		        case 5:
		        	System.out.println("---------- Enter the origin country name of the Plant ------------");
			        sc.nextLine();
			        String country=sc.nextLine();
			        try {
			        	ArrayList<Plant> arr2=pdao.searchByOriginCountryName(country);
				        for(int i=0;i<arr2.size();i++) {
				            Plant t=arr2.get(i);
				            System.out.println("---------------------");
					        System.out.println("ID : "+ t.plantId+" ");
					        System.out.println("Plant Name : "+ t.plantName+" ");
					        System.out.println("Origin Country Name : "+ t.originCountryName+" ");
					        System.out.println("Plant Type : "+ t.plantType+" ");
					        System.out.println("Cost : "+ t.cost);
					       }
				        System.out.println();
				    } catch (WrongALException e) {
				        System.out.println(e);
				    }
		        break;
		        
		        case 6:
		        	ArrayList<Plant> arr3=pdao.searchOutdoorPlantsWithSunlight();
			        for(int i=0;i<arr3.size();i++) {
			            Plant t=arr3.get(i);
			            System.out.println("---------------------");
				        System.out.println("ID : "+ t.plantId+" ");
				        System.out.println("Plant Name : "+ t.plantName+" ");
				        System.out.println("Origin Country Name : "+ t.originCountryName+" ");
				        System.out.println("Plant Type : "+ t.plantType+" ");
				        System.out.println("Cost : "+ t.cost);
				       }
			        System.out.println();
		        break;
		        
		        case 7:
		        	System.out.println("---------- Enter whether search is to be daily, weekly or monthly ----------");
			        sc.nextLine();
			        String water=sc.nextLine();
			        int ans=pdao.countPlantsByWaterSupplyFrequency(water);
			        System.out.println("Total number of plants with condition "+water+" is "+ans);
			        System.out.println();
		        break;
		        
		        case 8:
			        System.out.println("Exiting from system.....");
			        System.out.println("Exited!!!");
			        System.out.println("------------------------------------");
			        System.out.println("Thank you for using this System");
			        System.out.println("------------------------------------");
		        break;
		        
		        default:
		        	try {
		        		throw new WrongIDException("Invalid choice");
		        		} catch(WrongIDException e) {
		        			System.out.println(e);
		        		}
		        	System.out.println(); 
		        }
		        } catch( Exception e) {
		        	System.out.println();
		        	System.out.println("Error : Enter number to perform the given operation!!!!");
		        	System.out.println();
		        	sc.nextLine();
		    }
			} while(ch!=8); 
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		sc.close();
		}   
	}
