package com.laptrinhjavaweb.controller;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;



public class BuildingController {
	

	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/estate32020module1part1";
	static final String USER = "root";
	static final String PASS = "1234";

	public static void main(String[] args) {
		  IBuildingService buildingService= new BuildingService(); 

			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setId(1L);
			buildingDTO.setName("Nam Giao Building Tower 9022");
			//buildingDTO.setName("text");
			//buildingDTO.setWard("text12");
			//buildingService.insert(buildingDTO);
			buildingService.update(buildingDTO);
			//buildingService.delete(buildingDTO);
		
	}
}
