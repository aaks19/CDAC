namespace _001DemoMyAttributes
{
    public class Table: Attribute
    {
		private string _Name;

		public string Name
		{
			get { return _Name; }
			set { _Name = value; }
		}

	}

	public class Column: Attribute
	{
		private string _ColumnName;
		private string _ColumnType;

		public string ColumnType
		{
			get { return _ColumnType; }
			set { _ColumnType = value; }
		}

		public string ColumnName
		{
			get { return _ColumnName; }
			set { _ColumnName = value; }
		}

	}
}
