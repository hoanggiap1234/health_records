package com.viettel.etc.kafka.domain.healthcare_result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Comment
 *
 * @author hungnd
 */
@Data
public class DrugInformation {

	@NotNull
	@JsonProperty("tenThuoc")
	private String drugName;

	@NotNull
	@JsonProperty("maThuoc")
	private String drugCode;

	@JsonProperty("maNhom")
	private String groupId;

	@NotNull
	@JsonProperty("donViTinh")
	private String unit;

	@NotNull
	@Length(max = 5)
	@JsonProperty("duongDung")
	private String methodCode;

	@NotNull
	@JsonProperty("soLuong")
	private Integer quantity;

	@JsonProperty("lieuDungSang")
	private Integer morningAmount;

	@JsonProperty("lieuDungTrua")
	private Integer noonAmount;

	@JsonProperty("lieuDungChieu")
	private Integer afternoonAmount;

	@JsonProperty("lieuDungToi")
	private Integer eveningAmount;

	@JsonProperty("lieuDung")
	private String dosageDescription;

	@JsonProperty("ghiChu")
	private String notes;

	@NotNull
	@JsonProperty("ngayYLenh")
	private Date decisionDate;

}
