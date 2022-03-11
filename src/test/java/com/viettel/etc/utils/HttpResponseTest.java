package com.viettel.etc.utils;import lombok.Data;import org.springframework.http.HttpHeaders;import org.springframework.http.HttpStatus;import com.viettel.etc.utils.HttpResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class HttpResponseTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void HttpResponse (){
		assertThat(HttpResponse.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}