package edu.kh.contact.ct.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	private int contactNo;
	private String contactName;
	private String contactInfo;
	private String contactDate;
	private String contactDel;
	private String contactPhone;
	private String star;
	
}
