package uk.AntonSibgatulin.Beer.Actives;

import uk.AntonSibgatulin.User.User;

public class AurumActives implements IActives{
public double price  = 0;
public int infinity = 100000000;
	public AurumActives() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return infinity;
	}

	@Override
	public void buy(User user, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInd() {
		// TODO Auto-generated method stub
		return 0;
	}

}
