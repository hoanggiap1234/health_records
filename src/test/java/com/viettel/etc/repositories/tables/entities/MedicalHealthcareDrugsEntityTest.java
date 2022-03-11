package com.viettel.etc.repositories.tables.entities;import com.viettel.etc.utils.Constants;import lombok.Data;import lombok.NoArgsConstructor;import org.hibernate.annotations.CreationTimestamp;import org.hibernate.annotations.UpdateTimestamp;import javax.persistence.*;import java.io.Serializable;import java.time.LocalDateTime;import java.util.Date;import com.viettel.etc.repositories.tables.entities.MedicalHealthcareDrugsEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareDrugsEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareDrugsEntity (){
		BeanMatchers.registerValueGenerator(new ValueGenerator<LocalDateTime>() {
			public LocalDateTime generate() {
				return null;  // Change to generate random instance
		}
		}, LocalDateTime.class);
		assertThat(MedicalHealthcareDrugsEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}