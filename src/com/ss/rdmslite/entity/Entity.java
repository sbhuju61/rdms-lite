package com.ss.rdmslite.entity;

import java.util.List;

public interface Entity {
	public void createRecord(String data);
	public List <String> readRecord();
	public void updateRecord(int id, String data);
	public void deleteRecord(int id);
}
