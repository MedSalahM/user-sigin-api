package com.mms.users.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mms.users.domain.Region;

@Repository
public class RegionRepo {

	
	public List<Region> regions(){
		
		List<Region> regions = new ArrayList<>();
		;
		
		regions.add(Region.builder().id(1).regionLabel("TEBESSA").build());
		regions.add(Region.builder().id(2).regionLabel("ANNABA").build());
		
		return regions;
		
		
	}
	
	
	
	public Region getByid(Integer regionId) {
		
		
		return regions().stream().filter(e->e.getId().equals(regionId)).findFirst().get();
		
		
		
	}
}
