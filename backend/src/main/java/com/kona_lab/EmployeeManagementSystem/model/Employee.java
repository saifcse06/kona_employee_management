package com.kona_lab.EmployeeManagementSystem.model;

import javafx.scene.text.Text;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "employees")
public class Employee {

	private long id;
	private String name;
	private String emailId;
	private String contactNumber;
	private String gender;
	private Date dateOfBirth;
	private String profilePicture;
	private String department;
	private String programmingLanguage;
	private String presentAddress;
	public Employee(){}
	public Employee(String name, String emailId, String contactNumber, String gender, Date dateOfBirth, String profilePicture, String department, String programmingLanguage, String presentAddress) {
		this.name = name;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.profilePicture = profilePicture;
		this.department = department;
		this.programmingLanguage = programmingLanguage;
		this.presentAddress = presentAddress;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	//@NotEmpty(message = "Please provide valid name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email_address", nullable = false)
	//@NotEmpty(message = "Please provide valid email address")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "contact_number",nullable = false)
	//@NotEmpty(message = "Please provide valid contact number")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "gender",nullable = false)
	//@NotEmpty(message = "Please select gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "date_of_birth",nullable = false)
	//@NotEmpty(message = "Please provide valid date of birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "profile_picture",nullable = false)
	//@NotEmpty(message = "Please upload profile picture")
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Column(name = "department",nullable = true)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "programming_language",nullable = true)
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	@Column(name = "present_address",nullable = true)
	public String getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", emailId=" + emailId
				+ "]";
	}
	
}
