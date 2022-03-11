package com.viettel.etc.dto;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.TeleCareException;
import java.util.Arrays;
import com.viettel.etc.dto.IndexType;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class IndexTypeTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void IndexType (){
		assertThat(IndexType.values().length, Matchers.greaterThan(1));
	}

}