package com.mms.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mms.users.domain.Region;
import com.mms.users.repo.RegionRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {

	private final RegionRepo repo;
	
	
	public List<Region> allRegions(){
		
		
		return repo.regions();
		
	}
}
