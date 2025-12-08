using System.Reflection;
using System.Runtime.Serialization;

namespace _08DemoReflection
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //string path = 
            //    "D:\\IACSD\\IACSDDemos\\MathLib\\bin\\Debug\\net8.0\\MathLib.dll";

            Console.WriteLine("Enter the assembly path: ");
            string path = Console.ReadLine();

            Console.WriteLine();
            Assembly assembly = Assembly.LoadFrom(path);

            Type[] allTypes = assembly.GetTypes();
            foreach (Type type in allTypes)
            {
                Console.WriteLine("-------------------------------------");
                Console.WriteLine(type.FullName);

                Attribute[] allAttributesOfType =
                    type.GetCustomAttributes().ToArray();

                bool isTypeSerializable = false;
            
                foreach (Attribute attribute in allAttributesOfType)
                {
                    if (attribute is SerializableAttribute)
                    {
                        isTypeSerializable = true;
                        break;
                    }
                }

                if (isTypeSerializable)
                {
                    Console.WriteLine(type.Name + " is serializable!");
                }
                else
                {
                    Console.WriteLine(type.Name + " is NOT serializable!!");
                }

                Console.WriteLine("-------------------------------------");


                MethodInfo[] methods = type.GetMethods();

                Console.WriteLine();
                foreach (MethodInfo method in methods)
                {
                    Console.Write( " - " + method.Name);

                    ParameterInfo[] allParams = method.GetParameters();

                    Console.Write(" ( ");
                    foreach (ParameterInfo parameter in allParams)
                    {
                        Console.Write(" " + parameter.ParameterType);
                        Console.Write(" " + parameter.Name + " ");
                    }
                    Console.Write(" ) ");
                    Console.WriteLine();
                }
            }

            Console.ReadLine();

        }
    }
}
