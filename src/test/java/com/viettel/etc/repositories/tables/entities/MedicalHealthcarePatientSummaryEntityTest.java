package com.viettel.etc.repositories.tables.entities;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Data;import lombok.NoArgsConstructor;import org.springframework.data.annotation.CreatedDate;import org.springframework.data.annotation.LastModifiedDate;import javax.persistence.*;import java.io.Serializable;import java.util.Date;import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientSummaryEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcarePatientSummaryEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcarePatientSummaryEntity (){
		assertThat(MedicalHealthcarePatientSummaryEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}