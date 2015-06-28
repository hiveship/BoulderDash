package nantel.java.boulder.models.matrix;

import java.util.Map;

import nantel.java.boulder.models.entities.EmptySpace;
import nantel.java.boulder.models.entities.Entity;
import nantel.java.boulder.models.exceptions.NotImplementedException;

public class EntityMatrixMapImpl implements EntityMatrix
{
	@SuppressWarnings("unused")
	private final Map<Coordinates, Entity> data;

	public EntityMatrixMapImpl()
	{
		throw new NotImplementedException("The EntityMatrixMap has not been implemented yet. Use EntityMatrix2DArray instead.");
	}

	@Override
	public void display()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setContentAt(final int x, final int y, final Entity entity)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public Entity getContentAt(final int x, final int y)
	{
		// TODO Auto-generated method stub
		return new EmptySpace(); //TODO just an entity is returned cause of Eclipse null analysis.
	}

	@Override
	public int getRowsCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnsCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	final class Coordinates
	{
		private int x;
		private int y;

		public Coordinates(final int x, final int y)
		{
			this.x = x;
			this.y = y;
		}

		public int getX()
		{
			return this.x;
		}

		public void setX(final int x)
		{
			this.x = x;
		}

		public int getY()
		{
			return this.y;
		}

		public void setY(final int y)
		{
			this.y = y;
		}
	}

}
