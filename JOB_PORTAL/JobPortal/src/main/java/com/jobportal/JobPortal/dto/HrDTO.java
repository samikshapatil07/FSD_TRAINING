package com.jobportal.JobPortal.dto;

import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.Hr;

public class HrDTO {
	private int id;
	private String name;
	private String companyName;
	private String username;
	
	 //implementing dto by convertToDTO
    
	//here we are converting a list of Hr entity objects into a list of Hr
    //we are using this when we have multiple Hr objects (List<Hr>) 
    //and we want to convert all  into DTOs to return as a list in response in API
	//we are using this for "getAllHrs" in hr service
    public static List<HrDTO> converttoDto(List<Hr> list) {
    	//creste a new list to store dtos
        List<HrDTO> listDto = new ArrayList<>();
        
        //iterate pver each hr entity in the list
        list.stream().forEach(hr -> {
        	HrDTO dto = new HrDTO(); //create new dto for hr
        	//set values from gr entity to dto
        	 dto.setId(hr.getId());
             dto.setName(hr.getName());
             dto.setCompanyName(hr.getCompanyName());
             
             if (hr.getUser() != null && hr.getUser().getUsername() != null) {
                 dto.setUsername(hr.getUser().getUsername());
             } else {
                 dto.setUsername("Username not available");
             }
            //add this dto to list
            listDto.add(dto);
        });

        return listDto; //return final dto
    }
    
    //here we are converting a single hr entity object into HrDTO
    //we are using this when you have only one hr object not a list of hr
    //for "getHrByUsername", "getHrById" in hr service
    public static HrDTO converttoDto(Hr hr) {
    	
    	HrDTO dto = new HrDTO(); //create new dto object
    	
    	 dto.setId(hr.getId());
         dto.setName(hr.getName());
         dto.setCompanyName(hr.getCompanyName());
         //directly fect the username
         dto.setUsername(hr.getUser().getUsername());
        return dto; //return the dto obj
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	
}
