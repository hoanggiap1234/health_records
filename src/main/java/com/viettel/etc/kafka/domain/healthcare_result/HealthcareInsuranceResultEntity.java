package com.viettel.etc.kafka.domain.healthcare_result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthcareInsuranceResultEntity {
	private Integer historyId;
	private Integer actionBy;

	@Valid
	@JsonProperty("THONGTINCHUNG")
	private GeneralInformation generalInformation;

	@Valid
	@JsonProperty("THONGTINBENH")
	private List<DiseaseInformation> diseaseInformations;

	@Valid
	@JsonProperty("THONGTINDICHVU")
	private List<ServiceInformation> serviceInformations;

	@Valid
	@JsonProperty("THONGTINTHUOC")
	private List<DrugInformation> drugInformations;

	@Valid
	@JsonProperty("THONGTINKETQUA")
	private List<ClinicalResults> clinicalResults;
}
