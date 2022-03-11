package com.viettel.etc.repositories.tables.entities;import lombok.Data;import lombok.NoArgsConstructor;import javax.persistence.*;import java.io.Serializable;import java.sql.Date;import com.viettel.etc.repositories.tables.entities.RegimensEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class RegimensEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void RegimensEntity (){
		assertThat(RegimensEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}