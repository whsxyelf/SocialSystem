package com.whsxyelf.social.bean;

public class Collect {
	private int collectionId;
	private int userNo;
	private int collectionType;
	private int collectedId;
	
	public Collect() {
		
	}
	
	public Collect(int collectionId, int userNo, int collectionType, int collectedId) {
		super();
		this.collectionId = collectionId;
		this.userNo = userNo;
		this.collectionType = collectionType;
		this.collectedId = collectedId;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(int collectionType) {
		this.collectionType = collectionType;
	}
	public int getCollectedId() {
		return collectedId;
	}
	public void setCollectedId(int collectedId) {
		this.collectedId = collectedId;
	}

	@Override
	public String toString() {
		return "Collection [collectionId=" + collectionId + ", userNo=" + userNo + ", collectionType=" + collectionType
				+ ", collectedId=" + collectedId + "]";
	}
	
}
