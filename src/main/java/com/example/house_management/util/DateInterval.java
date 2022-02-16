package com.example.house_management.util;

import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;

public enum DateInterval {

	SHOW_ALL(0, LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.systemDefault())),
	LAST_SIX_MONTHS(50, LocalDateTime.now().minusMonths(6)),
	LAST_TWELVE_MONTHS(100, LocalDateTime.now().minusMonths(12));

	private Integer value;
	private LocalDateTime filterDate;

	private DateInterval(Integer value, LocalDateTime filterDate) {
		this.value = value;
		this.filterDate = filterDate;

	}

	public Integer getValue() {
		return value;
	}

	public LocalDateTime getFilterDate() {
		return filterDate;
	}

	public static LocalDateTime getFilterDateFromValue(Integer value) {
		for (DateInterval dateInterval : DateInterval.values()) {
			if (dateInterval.getValue().equals(value)) {
				return dateInterval.getFilterDate();
			}
		}
		return null;
	}

}
