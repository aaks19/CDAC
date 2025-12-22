using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _10DemoFeatures
{
    //Imagine this code is auto generated
    public partial class Student
    {
		partial void Validate(string propertyName, object value);

		private int _Age;

		public int Age
		{
			get
            {
                return _Age; 
			}
			set
			{
				Validate("Age", value);
                _Age = value; 
			}
		}

		public string GetStudentDetails()
		{
			return "Some student details...";
		}

	}
}
