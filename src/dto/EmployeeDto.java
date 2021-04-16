package dto;

public class EmployeeDto {
	private int seq;
	private String name;
	private String phone;
	private String email;
	private String hiredate;
	
	public EmployeeDto() {
	}

	
	public EmployeeDto(int seq, String name, String phone, String email, String hiredate) {
		super();
		this.seq = seq;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.hiredate = hiredate;
	}

	// 추가용
	public EmployeeDto(String name, String phone, String email, String hiredate) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.hiredate = hiredate;
	}

	// 수정용
	public EmployeeDto(int seq, String phone, String hiredate) {
		super();
		this.seq = seq;
		this.phone = phone;
		this.hiredate = hiredate;
	}

	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "EmployeeDto [name=" + name + ", phone=" + phone + ", email=" + email + ", hiredate=" + hiredate + "]";
	}
	
	
}
