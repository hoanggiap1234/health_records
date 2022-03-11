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
public class GeneralInformation {
	@JsonProperty("maBenhNhan")
	private Integer patientId;

	@JsonProperty("bacSiKham")
	private String doctorName;

	@JsonProperty("maBacSi")
	private String doctorCode;

	@NotNull
	@JsonProperty("datKhamId")
	private Integer bookingId;

	@JsonProperty("trangThai")
	private Integer bookingStatus;

	@NotNull
	@JsonProperty("maCSKCB")
	private String healthfacilitiesCode;

	@NotNull
	@JsonProperty("loaiKCB")
	private Integer typeOfExamination;

	@NotNull
	@JsonProperty("ngayVao")
	private Date examinationDate;

	@NotNull
	@JsonProperty("ngayRa")
	private Date finishExaminationDate;

	@JsonProperty("soNgayDieuTri")
	private Integer treatmentDayNumber;

	@NotNull
	@JsonProperty("lyDoKham")
	private String reasonsMedicalexamination;

	@JsonProperty("benhSu")
	private String symptoms;

	@JsonProperty("khamMatPhaiCoKinh")
	private String rightEyeWithGlasses;

	@JsonProperty("khamMatPhaiKhongKinh")
	private String rightEyeWithoutGlasses;

	@JsonProperty("khamMatTraiCoKinh")
	private String leftEyeWithGlasses;

	@JsonProperty("khamMatTraiKhongKinh")
	private String leftEyeWithoutGlasses;

	@JsonProperty("khamDaNiemMac")
	private String bodySkin;

	@JsonProperty("khamToanThanKhac")
	private String bodySkinOther;

	@JsonProperty("khamTimMach")
	private String bodyPartHeart;

	@JsonProperty("khamHoHap")
	private String bodyPartRespiratory;

	@JsonProperty("khamTieuHoa")
	private String bodyPartDigest;

	@JsonProperty("khamTietNieu")
	private String bodyPartUrinary;

	@JsonProperty("khamCoXuongKhop")
	private String bodyPartOsteoarthritis;

	@JsonProperty("khamNoiTiet")
	private String bodyPartEndocrine;

	@JsonProperty("khamThanKinh")
	private String bodyPartNerve;

	@JsonProperty("khamTamThan")
	private String bodyPartMental;

	@JsonProperty("khamNgoaiKhoa")
	private String bodyPartSurgical;

	@JsonProperty("khamPhuKhoa")
	private String bodyPartGynecology;

	@JsonProperty("khamTMH")
	private String bodyPartEarNoseThroat;

	@JsonProperty("khamRHM")
	private String bodyPartDentomaxillofacial;

	@JsonProperty("khamMat")
	private String bodyPartEye;

	@JsonProperty("khamDaLieu")
	private String bodyPartSkin;

	@JsonProperty("khamDinhDuong")
	private String bodyPartNutrition;

	@JsonProperty("khamVanDong")
	private String bodyPartMotive;

	@JsonProperty("khamDanhGia")
	private String bodyPartEvaluation;

	@JsonProperty("khamKhac")
	private String bodyPartOther;

	@JsonProperty("henTaiKham")
	private Date reExaminationDate;

	@JsonProperty("ketLuan")
	private String concludesDisease;

	@JsonProperty("tuVan")
	private String treatmentDirection;

	@JsonProperty("mach")
	private Integer pulse;

	@JsonProperty("nhietDo")
	private Double temperature;

	@JsonProperty("huyetApTT")
	private Integer bloodPressureMin;

	@JsonProperty("huyetApTD")
	private Integer bloodPressureMax;

	@JsonProperty("nhipTho")
	private Integer breath;

	@JsonProperty("canNang")
	private Double weight;

	@JsonProperty("chieuCao")
	private Double height;

	@JsonProperty("chiSoBmi")
	private Double bmi;

	@JsonProperty("vongBung")
	private Integer waistCircumference;

	@JsonProperty("ngayDo")
	private Date resultDate;

	public Double getBmi() {
		if (this.weight != null && this.height != null &&
				this.weight > 0 && this.height > 0) {
			double heightm = (double) this.height / 100;

			return this.weight / Math.pow(heightm, 2);
		}
		return null;
	}
}
