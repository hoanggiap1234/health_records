package com.viettel.etc.repositories.tables.entities;import com.fasterxml.jackson.annotation.JsonBackReference;import com.viettel.etc.utils.Constants;import lombok.Data;import lombok.NoArgsConstructor;import org.hibernate.annotations.CreationTimestamp;import org.hibernate.annotations.UpdateTimestamp;import javax.persistence.*;import java.util.Date;import com.viettel.etc.repositories.tables.entities.MedicalHealthcareHistoriesAttachmentsEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareHistoriesAttachmentsEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareHistoriesAttachmentsEntity (){
		assertThat(MedicalHealthcareHistoriesAttachmentsEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}