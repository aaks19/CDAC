using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _15DemoThreads
{
    public partial class Form1 : Form
    {
        Thread thread1 = null;
        Thread thread2 = null;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        public void F1()
        {
            for (int i = 0; i < 10; i++)
            {

                listBox1.Items.Add(i.ToString());
                Thread.Sleep(1000);
                if (i == 4)
                {
                    thread2.Join();
                }
            }
        }

        public void F2()
        {
            for (int i = 10; i > 0; i--)
            {
                listBox2.Items.Add(i.ToString());
                Thread.Sleep(1000);
                if (i == 7)
                {
                    thread1.Join();
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            thread1 = new Thread(F1);
            thread1.Start();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            thread2 = new Thread(F2);
            thread2.Start();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            thread1.Suspend();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            thread2.Suspend();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            thread1.Resume();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            thread2.Resume();
        }

        private void button8_Click(object sender, EventArgs e)
        {
            thread1.Abort();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            thread2.Abort();
        }
    }
}
