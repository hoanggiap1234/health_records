package com.viettel.etc.repositories.tables.entities;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class RegimensGroupsEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void RegimensGroupsEntity (){
		assertThat(RegimensGroupsEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}