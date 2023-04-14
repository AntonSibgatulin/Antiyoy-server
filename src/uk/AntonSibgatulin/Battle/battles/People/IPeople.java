package uk.AntonSibgatulin.Battle.battles.People;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Player;
import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.Battle;

public interface IPeople {
public Player getPlayer();
public int getHealth();
public boolean isDied(int power);
public PeopleType getType();
public int getPower();
public int getWeight();
public int getIdHome();
public int getId();
public void shot(Battle battle,IPeople[] player);
public void shot(Battle battle,ArrayList<IPeople> player);
public boolean isdied();
public Position getPosition();
public void setPosition(int x,int y);
public void move(int x,int y);
public int getLevel();

public void shotme(Battle battle,IPeople people,boolean isdied,int power);

}
