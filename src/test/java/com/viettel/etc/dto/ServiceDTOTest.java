package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Data;import lombok.NoArgsConstructor;import javax.validation.constraints.NotNull;import com.viettel.etc.dto.ServiceDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class ServiceDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void ServiceDTO (){
		assertThat(ServiceDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}