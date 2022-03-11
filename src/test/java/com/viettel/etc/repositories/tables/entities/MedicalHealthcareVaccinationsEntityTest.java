package com.viettel.etc.repositories.tables.entities;import java.io.Serializable;import javax.persistence.*;import javax.validation.constraints.NotNull;import com.viettel.etc.utils.Constants;import lombok.Data;import lombok.NoArgsConstructor;import java.util.Date;import java.util.Objects;import com.viettel.etc.repositories.tables.entities.MedicalHealthcareVaccinationsEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareVaccinationsEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareVaccinationsEntity (){
		assertThat(MedicalHealthcareVaccinationsEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}