package com.neu.edu.wtp.utility;

import javax.persistence.AttributeConverter;

import com.neu.edu.wtp.pojo.UserType;

public class UserTypeConverter implements AttributeConverter<UserType, String> {

	@Override
	public String convertToDatabaseColumn(UserType attribute) {
		// TODO Auto-generated method stub
		switch (attribute.ordinal()) {
		case 0:
			return "C";
		case 1:
			return "E";
		case 2:
			return "M";
		case 3:
			return "A";
		default:
			throw new IllegalArgumentException("Unknown-----------------------------" + attribute);
		}
		
	}

	@Override
	public UserType convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		switch (mapStringToInt(dbData)) {
		case 0:
			return UserType.Customer;
		case 1:
			return UserType.Employee;
		case 2:
			return UserType.Manager;
		case 3:
			return UserType.Admin;
		default:
			throw new IllegalArgumentException("Unknown---------------------------" + dbData);
		}
	}

	public int mapStringToInt(String type){
		if(type.equals("C")) return 0;
		if(type.equals("E")) return 1;
		if(type.equals("M")) return 2;
		if(type.equals("A")) return 3;
		return -1;
	}
}
