package com.viettel.etc.dto;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareHistoryAttachmentConsultantFileDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareHistoryAttachmentConsultantFileDTO (){
		assertThat(MedicalHealthcareHistoryAttachmentConsultantFileDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}