package de.hdm.team7.shared.geschaeftsobjekte;

public class ZuordnungBGBG extends Zuordnung {

	private int z_BGBG_id;
	private int elternteilId;
	private int kinderteilId;
	
	
	public ZuordnungBGBG() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId()	{
		return z_BGBG_id;
	}
	
	public int getElternteilId()	{
		return elternteilId;
	}
	
	public void setElternteilId(int elternteilId)	{
		this.elternteilId = elternteilId;
	}
	
	public int getKinderteilId()	{
		return kinderteilId;
	}
	
	public void setKinderteilId(int kinderteilId)	{
		this.kinderteilId = kinderteilId;
	}

}
