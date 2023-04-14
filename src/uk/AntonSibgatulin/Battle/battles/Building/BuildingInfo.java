package uk.AntonSibgatulin.Battle.battles.Building;

public class BuildingInfo {
	public int price  = 0;
	public int nalog = 0;
	public int level = 0;
	public int dprice = 0;
	
	public BuildingInfo(int price,int level,int nalog,int dprice) {
		this.price = price;
		this.level = level;
		this.dprice = dprice;
		this.nalog = nalog;
	}
	public void update(){
		price +=dprice;
	}

}
