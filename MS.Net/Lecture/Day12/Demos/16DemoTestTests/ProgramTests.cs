using Microsoft.VisualStudio.TestTools.UnitTesting;
using _16DemoTest;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _16DemoTest.Tests
{
    [TestClass()]
    public class ProgramTests
    {
        [TestMethod()]
        public void MainTest()
        {
            Assert.Fail();
        }
    }

    [TestClass()]
    public class MathsTests
    {
        [TestMethod()]
        public void AddTest()
        {
            int expectedResult = 300;

            Maths obj = new Maths();
            int x = 100;
            int y = 200;

            int actualResult = obj.Add(x, y);
            Assert.AreEqual(expectedResult, actualResult);
        }
    }

   
}