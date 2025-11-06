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
public class BookAppointmentResponse {
	private Long appointmentId;
	private String doctorName;
	private LocalDateTime appointmentDate;
	private Status status = Status.SCHEDULED;
	private String message;
}
