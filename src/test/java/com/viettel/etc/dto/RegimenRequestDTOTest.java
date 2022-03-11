package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Data;import lombok.NoArgsConstructor;import java.util.List;import com.viettel.etc.dto.RegimenRequestDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class RegimenRequestDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void RegimenRequestDTO (){
		assertThat(RegimenRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}