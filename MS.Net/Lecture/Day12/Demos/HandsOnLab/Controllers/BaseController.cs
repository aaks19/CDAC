using Microsoft.AspNetCore.Mvc;
using HandsOnLab.Filters;

namespace HandsOnLab.Controllers
{
    [LogFilter]
    public abstract class BaseController : Controller
    {
    }
}
