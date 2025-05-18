package com.lms.enums;

public enum City {
  MUMBAI,PUNE,CHENNAI
}
/*
 * values() : iterates and gives all the cities
 * valueOf(<value>) : tell u if this is a legitimate value
 * 
 * how can i convert enums to list
 * list list =  new ArrayList<>(Array.asList(cities));
 * 
 * to make list of sring out of enum :
 * List<String> list  = new ArrayList<>();
 * for(City city : cities){
 * list add(city.toString());
 * }
 * */
