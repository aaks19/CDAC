using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _10DemoFeatures
{
    //Developer Written
    public partial class Student
    {
        partial void Validate(string propertyName, object value)
        {
            //Logic to validate the data here
        }

	}
}
