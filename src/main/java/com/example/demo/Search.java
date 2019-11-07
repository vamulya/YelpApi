package com.example.demo;

public class Search {

	private int id;
	private int alias;
	private int name;
	public String[] businesses; ;
	public String[] getBusinesses() {
		return businesses;
	}
	public void setBusinesses(String[] businesses) {
		this.businesses = businesses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAlias() {
		return alias;
	}
	@Override
	public String toString() {
		return "Search [id=" + id + ", alias=" + alias + ", name=" + name + "]";
	}
	public void setAlias(int alias) {
		this.alias = alias;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	
}
