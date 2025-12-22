using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _12DemoDB.Model
{
    public class Emp
    {
        public int No { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }

        public override string ToString()
        {
            return string.Format("No = {0}, Name = {1}, Address = {2}", No, Name, Address);
        }
    }
}
