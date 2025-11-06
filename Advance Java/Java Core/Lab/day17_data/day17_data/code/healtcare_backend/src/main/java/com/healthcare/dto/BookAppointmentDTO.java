package com.healthcare.dto;

import java.time.LocalDateTime;

import com.healthcare.entities.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookAppointmentDTO {
	private Long doctorId;
	
	private LocalDateTime appointmentDateTime;
	
	
}
