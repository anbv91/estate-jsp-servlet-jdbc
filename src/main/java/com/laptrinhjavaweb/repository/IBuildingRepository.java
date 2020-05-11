package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingRepository extends JpaRepository<BuildingEntity> {
	void update(BuildingEntity buildingEntity);
	//void save(BuildingEntity newBuilding);
	void delete(Long id);
}
