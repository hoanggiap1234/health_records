package com.viettel.etc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
@NoArgsConstructor
public class HealthcareOverviewDto {
	private Patient patient;
	private HashMap<String, HealthcareIndex> healthcareIndex;

	@Data
	@NoArgsConstructor
	public static class Patient {
		Integer id;
		Integer genderId;
		String fullname;
		String phoneNumber;
		Date birthday;
	}

	@Data
	@NoArgsConstructor
	public static class HealthcareIndex {
		Object value;
		Date time;

		public HealthcareIndex(Object value, Date time) {
			this.value = value;
			this.time = time;
		}
	}
}
