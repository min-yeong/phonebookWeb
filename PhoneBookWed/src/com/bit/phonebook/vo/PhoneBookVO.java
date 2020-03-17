package com.bit.phonebook.vo;

public class PhoneBookVO {
	private Long PBid;
	private String PBname;
	private String PBhp;
	private String PBtel;
	
	public PhoneBookVO(Long id, String name, String hp, String tel) {
		super();
		PBid= id;
		PBname = name;
		PBhp = hp;
		PBtel = tel;
	}
	
	public PhoneBookVO(String name, String hp, String tel) {
		super();
		PBname = name;
		PBhp = hp;
		PBtel = tel;
	}
	
	public Long getPBid() {
		return PBid;
	}
	public void setPBid(Long pBid) {
		this.PBid = pBid;
	}

	public String getPBname() {
		return PBname;
	}
	public void setPBname(String pBname) {
		this.PBname = pBname;
	}
	public String getPBhp() {
		return PBhp;
	}
	public void setPBhp(String pBhp) {
		this.PBhp = pBhp;
	}
	public String getPBtel() {
		return PBtel;
	}
	public void setPBtel(String pBtel) {
		this.PBtel = pBtel;
	}
	
	@Override
	public String toString() {
		return "PhoneBookVO [PBid=" + PBid + ", PBname=" + PBname + ", PBhp=" + PBhp + ", PBtel=" + PBtel + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof PhoneBookVO) {
			PhoneBookVO other = (PhoneBookVO)arg0;
			return this.PBid == other.PBid;
		}
		return super.equals(arg0);
	}

}
