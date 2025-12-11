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
            return dbObject.Emps.ToList();
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
