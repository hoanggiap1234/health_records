package com.viettel.etc.kafka.domain.healthcare_result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
public class ServiceInformation {
	@JsonProperty("maDichVu")
	private String serviceCode;

	@NotNull
	@JsonProperty("tenDichVu")
	private String serviceName;

	@NotNull
	@JsonProperty("maNhom")
	private Integer groupId;

	@JsonProperty("donViTinh")
	private String unit;

	@JsonProperty("soLuong")
	private Integer quantity;

	@JsonProperty("ketLuan")
	private String concludes;

	@JsonProperty("ketQua")
	private String results;

	@JsonProperty("deNghi")
	private String suggestions;

	@JsonProperty("linkPacsView")
	private String linkViewDicom;

	@JsonProperty("ngayYLenh")
	@NotNull
	private Date decisionDate;

}
