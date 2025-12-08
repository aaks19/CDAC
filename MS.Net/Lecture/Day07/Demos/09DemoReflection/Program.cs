using System.Reflection;
namespace _09DemoReflection
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string path = "D:\\IACSD\\IACSDDemos\\MathLib\\bin\\Debug\\net8.0\\MathLib.dll";

            Assembly assembly = Assembly.LoadFrom(path);

            Type []types = assembly.GetTypes();
           
            foreach (Type type in types)
            {
                
                object dynamicallyCreatedObject =
                    assembly.CreateInstance(type.FullName);

                Console.WriteLine("Created Object of Type " + type.FullName);
                Console.WriteLine("-------------------");


                MethodInfo[] allMethods = type.GetMethods();

                foreach (MethodInfo method in allMethods)
                {
                    Console.WriteLine("-- calling "  + method.Name + " method");

                    ParameterInfo[] allParams = method.GetParameters();

                    object[]arguments = new object[allParams.Length];

                    for (int i = 0; i < allParams.Length; i++)
                    {
                        ParameterInfo parameter = allParams[i];

                        Console.WriteLine("Enter data for " + 
                            parameter.Name + " of type " +
                            parameter.ParameterType.ToString());

                        string valueOfParameter = Console.ReadLine();

                        object paramaterValue = Convert.ChangeType(valueOfParameter, parameter.ParameterType);

                        arguments[i] = paramaterValue;

                    }


                    object result = type.InvokeMember(method.Name,
                                      BindingFlags.Public |
                                      BindingFlags.Instance |
                                      BindingFlags.InvokeMethod,
                                      null,
                                      dynamicallyCreatedObject,
                                      arguments);

                    Console.WriteLine( "Method " + method.Name + " Result is = " +  result.ToString());
                    Console.WriteLine("------------------------");
                    Console.WriteLine();
                }
            }
            Console.ReadLine();
        }
    }
}





