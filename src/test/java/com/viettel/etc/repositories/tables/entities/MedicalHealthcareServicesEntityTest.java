package com.viettel.etc.repositories.tables.entities;import com.viettel.etc.utils.Constants;import lombok.Data;import lombok.EqualsAndHashCode;import lombok.NoArgsConstructor;import lombok.ToString;import org.hibernate.annotations.CreationTimestamp;import org.hibernate.annotations.UpdateTimestamp;import javax.persistence.*;import java.io.Serializable;import java.util.Date;import java.util.List;import com.viettel.etc.repositories.tables.entities.MedicalHealthcareServicesEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareServicesEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareServicesEntity (){
		assertThat(MedicalHealthcareServicesEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}