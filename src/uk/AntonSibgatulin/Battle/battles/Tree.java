package uk.AntonSibgatulin.Battle.battles;

import uk.AntonSibgatulin.Battle.Position;

public class Tree {
	public static final int SEC = 5;
public Position position = null;
public int weight = 1;
public int longer = 1;
public int id = 0;
public long time = System.currentTimeMillis();


	public Tree(Position position,int id) {
		this.position = position;
		this.id = id;
		
	}
	public void update(){
		if(System.currentTimeMillis()-time>1000*SEC){
			
			time = System.currentTimeMillis();
			this.weight +=1;
			this.longer +=1;
			
		}
	}
	

}
