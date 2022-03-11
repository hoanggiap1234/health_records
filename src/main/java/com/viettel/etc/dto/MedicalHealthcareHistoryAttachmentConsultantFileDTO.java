package com.viettel.etc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Comment
 *
 * @author
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalHealthcareHistoryAttachmentConsultantFileDTO {

	Integer attachmentId;

	String name;

	String file;

}
