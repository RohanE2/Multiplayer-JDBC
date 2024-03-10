package com.jspiders.songoperation;

public class SongOperation {
	
	private static int no;
	private String songName;
	private String artistName;
	private String prod;
	private double duration;
	private String releaseDt;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getReleaseDt() {
		return releaseDt;
	}
	public void setReleaseDt(String releaseDt) {
		this.releaseDt = releaseDt;
	}
	
	@Override
	public String toString() {
	return   " ♬ Music Player ♬   "+'\n'+
			 "╭───────────────────────────────────────────────────────────╮"+'\n'+ 	
			 "                      "+" "+ songName  +"                  "+'\n'+										
			 "                      "+"    "+artistName +"                  "+'\n'+      	
			 "    00.00"+"---|----------------------------------"+duration  +'\n'+         
			 "                "+"   "+" 🔊  ◀  ||  ■  ▶  ↻                 "+'\n'+     	
			 "╰───────────────────────────────────────────────────────────╯";
	}
}
