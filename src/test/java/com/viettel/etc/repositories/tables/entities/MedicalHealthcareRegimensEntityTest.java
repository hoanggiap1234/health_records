package com.viettel.etc.repositories.tables.entities;import java.io.Serializable;import javax.persistence.*;import lombok.Data;import lombok.NoArgsConstructor;import java.util.Date;import com.viettel.etc.repositories.tables.entities.MedicalHealthcareRegimensEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareRegimensEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareRegimensEntity (){
		assertThat(MedicalHealthcareRegimensEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}