package com.viettel.etc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 *
 * @author nguyenhungbk96@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordCallbackDTO {
	private Integer bookingId;
	private Integer historyId;
	private Integer actionBy;
}
