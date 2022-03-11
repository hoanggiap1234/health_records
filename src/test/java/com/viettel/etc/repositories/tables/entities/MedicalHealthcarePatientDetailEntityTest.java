package com.viettel.etc.repositories.tables.entities;import lombok.Data;import lombok.NoArgsConstructor;import javax.persistence.*;import java.io.Serializable;import java.util.Date;import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcarePatientDetailEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcarePatientDetailEntity (){
		assertThat(MedicalHealthcarePatientDetailEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}