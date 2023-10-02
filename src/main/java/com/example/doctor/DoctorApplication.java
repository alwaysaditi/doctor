package com.example.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class DoctorApplication {
	public static HashMap<String,String> slots = new HashMap<String,String>();
	public static void main(String[] args) {
		slots.put("2:30 pm","slot1");
		slots.put("3:00 pm","slot2");
		slots.put("3:30 pm","slot3");
		slots.put("4:00 pm","slot4");
		slots.put("4:30 pm","slot5");
		slots.put("5:00 pm","slot6");
		slots.put("5:30 pm","slot7");
		slots.put("6:00 pm","slot8");
		SpringApplication.run(DoctorApplication.class, args);
	}

}
