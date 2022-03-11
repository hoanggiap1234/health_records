package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viettel.etc.utils.Base64Util;
import lombok.Data;

/**
 * Comment
 *
 * @author os_spgp_gpdn148
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalHealthcareHistoryAttachmentDTO {
	Integer attachmentId;

	Integer groupType;

	Integer serviceId;

	String fileName;

	Integer historiesId;

	String name;

	String fileUrl;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	String url;

	@JsonProperty("file")
	public String getFile() {
		if (url != null && !Base64Util.isBase64String(url)) {
			return Base64Util.toBase64(url);
		}
		return null;
	}
}
