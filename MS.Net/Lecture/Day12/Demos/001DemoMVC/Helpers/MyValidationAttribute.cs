using _001DemoMVC.Models;
using System.ComponentModel.DataAnnotations;

namespace _001DemoMVC.Helpers
{
    public class CheckDuplicate:ValidationAttribute 
    {
        public override bool IsValid(object? value)
        {
            EFDBContext dbContext = new EFDBContext();
            var isValid = false;
            
            if (value!= null)
            {
                var result  = dbContext.Emps.ToList().Where(emp=> 
                            { 
                               return emp.name.ToLower() 
                                        == value.ToString().ToLower(); 
                            }).ToList();

                if (result.Count() <= 0)
                {
                    isValid = true;
                }
            }

            return isValid;

        }
    }
}
