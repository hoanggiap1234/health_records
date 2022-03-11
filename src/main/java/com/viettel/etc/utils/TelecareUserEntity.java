package com.viettel.etc.utils;

import com.viettel.etc.xlibrary.jwt.JwtRoleEntity;
import lombok.Data;

/**
 * Comment
 *
 * @author nguyenhungbk96@gmail.com
 */
@Data
public class TelecareUserEntity {
	Long telecareUserId;
	String keycloakUserId;
	String username;
	String password;
	String name;
	String role;
	String baseRole;
	String strJwtToken;
	String orgName;
	Long exp;
	Long iat;
	Long auth_time;
	JwtRoleEntity realm_access;
	JwtAccountEntity resource_access;
	String preferred_username;
	Boolean isVerifyToken;

	private void hasPermisison() throws TeleCareException {
		boolean pms = (resource_access.telecare != null || resource_access.telecare.getRoles().size() > 0);
		if (!pms) {
			throw new TeleCareException("No role");
		}
	}

	public boolean isPatient() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_CITIZEN.val());
	}

	public boolean isDoctor() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_DOCTOR.val());
	}

	public boolean isNurs() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_NURSING.val());
	}

	public boolean isClinic() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_CLINIC.val());
	}

	public boolean isAdmin() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_ADMIN.val());
	}

	public enum Role {
		TELECARE_DOCTOR("Telecare_Doctor"),
		TELECARE_NURSING("Telecare_Nursing"),
		TELECARE_CLINIC("Telecare_Clinic"),
		TELECARE_ADMIN("Telecare_Admin"),
		TELECARE_CITIZEN("Telecare_Citizen");

		private String role;

		private Role(String role) {
			this.role = role;
		}

		public String val() {
			return this.role;
		}
	}

	@Data
	public static class JwtAccountEntity {
		JwtRoleEntity account;
		JwtRoleEntity telecare;
	}
}
