package com.viettel.etc.repositories.tables.entities;import lombok.Data;import lombok.NoArgsConstructor;import javax.persistence.*;import java.io.Serializable;import java.sql.Date;import com.viettel.etc.repositories.tables.entities.PatientsHealthinsurancesEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class PatientsHealthinsurancesEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void PatientsHealthinsurancesEntity (){
		assertThat(PatientsHealthinsurancesEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}