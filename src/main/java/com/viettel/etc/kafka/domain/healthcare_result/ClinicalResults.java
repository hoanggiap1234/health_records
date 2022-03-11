package com.viettel.etc.kafka.domain.healthcare_result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
public class ClinicalResults {
	@NotNull
	@JsonProperty("stt")
	private Integer orderNumber;

	@NotNull
	@JsonProperty("maChiSo")
	private String indexCode;

	@NotNull
	@JsonProperty("maDichVu")
	private String serviceCode;

	@NotNull
	@JsonProperty("tenChiSo")
	private String indexName;

	@NotNull
	@JsonProperty("giaTri")
	private String value;

	@JsonProperty("donViTinh")
	private String unit;

	@JsonProperty("gioiHan")
	private String limit;

}
