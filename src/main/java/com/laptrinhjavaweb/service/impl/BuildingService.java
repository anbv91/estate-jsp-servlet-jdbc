package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;


public class BuildingService implements IBuildingService{

	IBuildingRepository buildingRepository = new BuildingRepository();
	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingDTO> results= new ArrayList<>();
		List<BuildingEntity> buildingEntites= buildingRepository.findAll();
		
		for(BuildingEntity item: buildingEntites) {
			BuildingDTO buildingDTO = new BuildingDTO();
			
			buildingDTO.setName(item.getName());
			buildingDTO.setWard(item.getWard());
			buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
			results.add(buildingDTO);
		}
		
		return results;
	}
	@Override
	public void update(BuildingDTO updateBuilding) {
		BuildingEntity buildingEntity= new BuildingEntity();
		buildingEntity.setId(updateBuilding.getId());
		buildingEntity.setName(updateBuilding.getName());
		buildingRepository.update(buildingEntity);
	}

	/*
	 * @Override public void save(BuildingDTO insertBuilding) { BuildingEntity
	 * buildingEntity= new BuildingEntity();
	 * buildingEntity.setId(insertBuilding.getId());
	 * buildingEntity.setName(insertBuilding.getName());
	 * buildingRepository.save(buildingEntity);
	 * 
	 * }
	 */
	@Override
	public void insert(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity= new BuildingEntity();
		buildingEntity.setName(newBuilding.getName());
		buildingEntity.setWard(newBuilding.getWard());
		buildingRepository.insert(buildingEntity);
		
		
	}
	@Override
	public void delete(BuildingDTO deleteBuildingDTO) {
		BuildingEntity buildingEntity= new BuildingEntity();
		buildingEntity.setName(deleteBuildingDTO.getName());
		buildingEntity.setWard(deleteBuildingDTO.getWard());
		//buildingEntity.setId(deleteBuildingDTO.getId());
		buildingRepository.delete(buildingEntity);
		
	}
	@Override
	public Optional<BuildingDTO> findById(Integer id) {
		BuildingEntity buildingEntity= new BuildingEntity();
		
		return null;
	}

}
