package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import com.fasterxml.jackson.annotation.JsonInclude.Include;import lombok.Data;import lombok.NoArgsConstructor;import java.util.Date;import com.viettel.etc.dto.VaccinationsDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class VaccinationsDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void VaccinationsDTO (){
		assertThat(VaccinationsDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}