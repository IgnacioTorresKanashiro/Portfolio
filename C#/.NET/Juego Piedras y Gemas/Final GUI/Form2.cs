using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Final_GUI
{
    public partial class Form2 : Form
    {
        Random rnd = new Random();
        public int cantIntentos = 0;
        public int cantPiedras = 0;
        public int piedrasParaGanar = 0;
        List<Boton> listBoton = new List<Boton>();

        public Form2()
        {
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            Form1 frm = (Form1)this.Owner;
            for (int i=0; i < Form1.indice; i++)
            {
                for(int j=0; j < Form1.indice; j++)
                {
                    Boton b = new Boton();
                    b.Size = new Size(50, 50);
                    // b.Size = new Size(50, frm.trackBar1.Value, this.Height / frm.trackbar1.Value); 
                    b.Location = new Point(i * b.Height, j * b.Width);
                    this.Controls.Add(b);
                    b.Click += new EventHandler(b_click);
                    b.Id = 'v';
                    listBoton.Add(b);
                }
            }

            int cantBotones = 0;
            foreach (Button bot in this.Controls)
            {
                if (bot.GetType() == typeof(Boton))
                {
                    cantBotones++;
                }
            }

            cantPiedras = (cantBotones * 60) / 100;
            piedrasParaGanar = ((cantPiedras * 60) / 100)+1;
            cantIntentos = ((cantBotones * 10) / 100)+1;

            if(cantIntentos==1)
            {
                cantIntentos++;
            }

            int todas = 0;
            int cp = 0;
            int num;
            while (todas<cantBotones)
            {
                num = rnd.Next(listBoton.Count());

                if (listBoton[num].Id == 'v')
                {
                    if (cp != cantPiedras)
                    {
                        listBoton[num].Id = 'r';
                        cp++;
                        todas++;
                    }

                    else
                    {
                        listBoton[num].Id = 'n';
                        todas++;
                    }
                }

                else
                {
                    rnd.Next(listBoton.Count());
                }

            }
                 
        }

        private void b_click(object sender, EventArgs e)
        {
            Boton b = (Boton)sender;

            if (b.Id == 'r')
            {
                b.BackColor = Color.Red;
                piedrasParaGanar--;

                if(piedrasParaGanar==0)
                {
                    MessageBox.Show("Ganaste el juego! Encontraste el 60% de las piedras rojas");
                    this.Close();
                }
            }

            else if (b.Id == 'n')
            {
                b.BackColor = Color.Black;
                cantIntentos--;

                if(cantIntentos==0)
                {
                    MessageBox.Show("Perdiste el juego, se te acabaron los intentos");
                    this.Close();
                }
            }
        }

        private void Form2_FormClosing(object sender, FormClosingEventArgs e)
        {
            this.Owner.Enabled = true;
        }

    }

    }

