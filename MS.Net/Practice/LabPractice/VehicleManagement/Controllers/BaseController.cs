using Microsoft.AspNetCore.Mvc;
using VehicleManagement.Filters;

namespace VehicleManagement.Controllers
{
    [AuthFilter]
    public class BaseController : Controller
    {
    }
}
