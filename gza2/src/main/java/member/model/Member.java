package member.model;

public class Member {
	private String id;
	private String memberName;
	private String password;
	private String phoneNum;
	private String birthday;
	
	public Member(String id, String memberName, String password, String phoneNum, String birthday) {
		super();
		this.id = id;
		this.memberName = memberName;
		this.password = password;
		this.phoneNum = phoneNum;
		this.birthday = birthday;
	}
	
	public String getId() {
		return id;
	}
	public String getMemberName() {
		return memberName;
	}
	public String getPassword() {
		return password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public String getBirthday() {
		return birthday;
	}
	
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}
	
	public void modifyMember(String newMemberName, String newPwd, String newPhoneNum, String newBirthday) {
		this.memberName=newMemberName;
		this.password=newPwd;
		this.phoneNum=newPhoneNum;
		this.birthday=newBirthday;
	}
	
}