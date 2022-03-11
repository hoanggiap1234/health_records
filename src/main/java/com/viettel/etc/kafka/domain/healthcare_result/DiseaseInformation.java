package com.viettel.etc.kafka.domain.healthcare_result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
public class DiseaseInformation {
	@NotNull
	@Length(max = 10)
	@JsonProperty("maBenh")
	private String diseasesCode;

	@NotNull
	@JsonProperty("tenBenh")
	private String diseasesName;

	@NotNull
	@JsonProperty("benhChinh")
	private Integer isMain;
}
