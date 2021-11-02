package ru.rsreu.RonzhinChistyakov09.datalayer.data.pilot;

public class Pilot {
	private int id;
	private PilotStatus status;
	private String fullName;
	
	public Pilot(int id, PilotStatus status, String fullName) {
		super();
		this.id = id;
		this.status = status;
		this.fullName = fullName;
	}
	
	public int getId() {
		return id;
	}
	
	public PilotStatus getStatus() {
		return status;
	}
	
	public String getFullName() {
		return fullName;
	}
}