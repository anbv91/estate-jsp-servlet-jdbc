package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Optional;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	List<BuildingDTO> findAll();
	void update(BuildingDTO updateBuilding);
	void insert(BuildingDTO newBuilding);
	void delete(BuildingDTO deleteBuildingDTO);
	Optional<BuildingDTO> findById(Integer id);
}
