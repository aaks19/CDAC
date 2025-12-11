using _001DemoMVC.Models;
using LoggerLib;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [EnableCors(PolicyName = "Policy1")]
    public class LogController : ControllerBase
    {
        [HttpPost]
        public void Post([FromBody] LogModel logModel)
        {
            FileLogger.CurrentLogger.Log(logModel.logMessage);
        }
    }

    public class LogModel
    {
        public string logMessage { get; set; }
    }
}
