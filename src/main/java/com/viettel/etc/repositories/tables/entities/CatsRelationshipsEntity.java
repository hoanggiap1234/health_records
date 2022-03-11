package com.viettel.etc.repositories.tables.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Autogen class Entity: Create Entity For Table Name Cats_relationships
 *
 * @author ToolGen
 * @date Thu Aug 13 22:25:22 ICT 2020
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "CATS_RELATIONSHIPS")
public class CatsRelationshipsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RELATIONSHIP_ID")
	Integer relationshipId;

	@Column(name = "CODE")
	String code;

	@Column(name = "NAME")
	String name;

	@Column(name = "ORDER_NUMBER")
	Integer orderNumber;

	@Column(name = "DESCRIPTION")
	String description;

	@Column(name = "IS_DELETE")
	Integer isDelete;

	@Column(name = "IS_ACTIVE")
	Integer isActive;

	@Column(name = "CREATE_USER_ID")
	Integer createUserId;

	@Column(name = "CREATE_DATE")
	Date createDate = new Date();

	@Column(name = "UPDATE_USER_ID")
	Integer updateUserId;

	@Column(name = "UPDATE_DATE")
	Date updateDate;
}