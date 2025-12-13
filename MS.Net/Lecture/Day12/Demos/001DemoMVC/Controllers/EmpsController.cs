using _001DemoMVC.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [EnableCors(PolicyName = "Policy1")]
    public class EmpsController : ControllerBase
    {
        EFDBContext dbObject = new EFDBContext();

        [HttpGet]
        public IEnumerable<Emp> Get()
        {
            return new List<Emp>() { 
             new Emp(){ no = 11, name = "mahesh1", address = "pune1" },
             new Emp(){ no = 12, name = "mahesh2", address = "pune2" },
             new Emp(){ no = 13, name = "mahesh3", address = "pune3" },
             new Emp(){ no = 14, name = "mahesh4", address = "pune4" },
             new Emp(){ no = 15, name = "mahesh5", address = "pune5" }
            };
            //return dbObject.Emps.ToList();
        }

        [HttpGet("{id}")]
        public Emp Get(int id)
        {
            return dbObject.Emps.Find(id);
        }

        
        [HttpPost]
        public void Post([FromBody] Emp emp)
        {
            dbObject.Emps.Add(emp);
            dbObject.SaveChanges();
        }

        [HttpPut("{id}")]
        public void Put(int id, [FromBody] Emp emp)
        {
            Emp empToUpdate = dbObject.Emps.Find(id);
            empToUpdate.name = emp.name;
            empToUpdate.address = emp.address;
            dbObject.SaveChanges();
        }

        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            Emp empToDelete = dbObject.Emps.Find(id);
            dbObject.Emps.Remove(empToDelete);
            dbObject.SaveChanges();
        }
    }
}
