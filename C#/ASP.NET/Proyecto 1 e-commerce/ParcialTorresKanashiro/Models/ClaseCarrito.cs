using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ParcialTorresKanashiro.Models
{
    public class ClaseCarrito
    {
        public string id;
        public Dictionary<Producto, int> listaProducto = new Dictionary<Producto, int>();

        public ClaseCarrito()
        {
            Id = HttpContext.Current.Session["email"].ToString();
        }

        public string Id
        {
            get
            {
                return id;
            }

            set
            {
                id = value;
            }
        }

        public Dictionary<Producto, int> ListaProducto
        {
            get
            {
                return listaProducto;
            }

            set
            {
                listaProducto = value;
            }
        }

        public static decimal? calcularTotal(Dictionary<Producto, int> lista)
        {
            decimal? total = 0;

            foreach (KeyValuePair<Producto, int> prod in lista)
            {
                decimal? precioProducto = prod.Key.precio * prod.Value;
                total += precioProducto;
            }

            return total;
        }
    }
}