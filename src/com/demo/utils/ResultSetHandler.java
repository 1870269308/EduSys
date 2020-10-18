package com.demo.utils;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {
	Object handle(ResultSet rs);
}
