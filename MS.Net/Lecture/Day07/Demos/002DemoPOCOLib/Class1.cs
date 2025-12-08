using _001DemoMyAttributes;

namespace _002DemoPOCOLib
{
	[Table(Name = "Employee")]
    public class Emp
    {
		private int _No;
		private string _Name;
		private string _Address;

		[Column(ColumnName = "Address", ColumnType ="varchar(50)")]
		public string Address
		{
			get { return _Address; }
			set { _Address = value; }
		}

        [Column(ColumnName = "Name", ColumnType = "varchar(50)")]
        public string Name
		{
			get { return _Name; }
			set { _Name = value; }
		}

        [Column(ColumnName = "No", ColumnType = "int")]
        public int No
		{
			get { return _No; }
			set { _No = value; }
		}

	}

	[Table(Name = "Dept")]
	public class Dept
	{
		private int _DNo;

		private string _DName;

		[Column(ColumnName = "Dname", ColumnType = "varchar(50)")]
		public string DName
		{
			get { return _DName; }
			set { _DName = value; }
		}

        [Column(ColumnName = "DNo", ColumnType = "int")]
        public int DNo
		{
			get { return _DNo; }
			set { _DNo = value; }
		}

	}
}
