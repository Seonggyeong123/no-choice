package auth.service;

public class User {

	private String id;
	private String memberName;

	public User(String id, String memberName) {
		this.id = id;
		this.memberName = memberName;
	}

	public String getId() {
		return id;
	}

	public String getMemberName() {
		return memberName;
	}
	
}