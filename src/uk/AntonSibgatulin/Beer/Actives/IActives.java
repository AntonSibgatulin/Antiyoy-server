package uk.AntonSibgatulin.Beer.Actives;

import uk.AntonSibgatulin.User.User;

public interface IActives {
	
public double getPrice();
public int getCol();
public void buy(User user,int col);
public int getInd();
}
