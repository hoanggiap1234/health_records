package com.viettel.etc.repositories;

import com.viettel.etc.dto.BookingInformationsDTO;

/**
 * Autogen class Repository Interface:
 *
 * @author toolGen
 * @date Mon Sep 14 10:19:45 ICT 2020
 */
public interface BookingInformationsRepository {
	public BookingInformationsDTO getById(int bookingId);
}