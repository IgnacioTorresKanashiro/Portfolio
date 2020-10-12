using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using ParcialTorresKanashiro.Models;

namespace ParcialTorresKanashiro.Controllers
{
    public class UsuariosController : Controller
    {
        private ParcialTorresKanashiroEntities db = new ParcialTorresKanashiroEntities();

        // GET: Usuarios
        [Filtros.FiltroAdmin]
        public ActionResult Index()
        {
            return View(db.Usuario.ToList());
        }

        public ActionResult Login(string idioma = "es-AR")
        {               
                Thread.CurrentThread.CurrentCulture = CultureInfo.CreateSpecificCulture(idioma);
                Thread.CurrentThread.CurrentUICulture = new CultureInfo(idioma);
                HttpContext.Session.Add("idioma", Thread.CurrentThread.CurrentCulture);
                return View();
        }

        public ActionResult VerificarUsuario(String email, String clave)
        {

            foreach (Usuario usr in db.Usuario.ToList())
            {
                if (usr.email.Equals(email) && usr.clave.Equals(clave))
                {
                    GuardarSesion(usr);
                    return RedirectToAction("Index", "Productos");
                }               
            }

            ViewBag.error = "El usuario y/o la clave son incorrectos";
            return View("Login");
        }

        // GET: Usuarios/Details/5
        [Filtros.FiltroAdmin]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Usuario usuario = db.Usuario.Find(id);
            if (usuario == null)
            {
                return HttpNotFound();
            }
            return View(usuario);
        }

        // GET: Usuarios/Create
        public ActionResult RegistrarUsuario()
        {
            return View();
        }

        // POST: Usuarios/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult CrearUsuario([Bind(Include = "id,nombre,apellido,email,clave")] Usuario usuario)
        {
            if (ModelState.IsValid)
            {
                usuario.esAdmin = false;
                db.Usuario.Add(usuario);
                db.SaveChanges();
                return RedirectToAction("Login", "Usuarios");
            }

            return View("Login");
        }

        // GET: Usuarios/Edit/5
        [Filtros.FiltroAdmin]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Usuario usuario = db.Usuario.Find(id);
            if (usuario == null)
            {
                return HttpNotFound();
            }
            return View(usuario);
        }

        // POST: Usuarios/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Filtros.FiltroAdmin]
        public ActionResult Edit([Bind(Include = "id,nombre,apellido,email,clave,esAdmin")] Usuario usuario)
        {
            if (ModelState.IsValid)
            {
                db.Entry(usuario).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(usuario);
        }



        // GET: Usuarios/Delete/5
        [Filtros.FiltroAdmin]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Usuario usuario = db.Usuario.Find(id);
            if (usuario == null)
            {
                return HttpNotFound();
            }
            return View(usuario);
        }

        // POST: Usuarios/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        [Filtros.FiltroAdmin]
        public ActionResult DeleteConfirmed(int id)
        {
            Usuario usuario = db.Usuario.Find(id);
            db.Usuario.Remove(usuario);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private void GuardarSesion(Usuario usr) {

            HttpContext.Session.Add("id", usr.id);
            HttpContext.Session.Add("nombre", usr.nombre);
            HttpContext.Session.Add("apellido", usr.apellido);
            HttpContext.Session.Add("email", usr.email);
            HttpContext.Session.Add("clave", usr.clave);
            HttpContext.Session.Add("esAdmin", usr.esAdmin);
            HttpContext.Session.Add("carrito", new ClaseCarrito());
            HttpContext.Session.Timeout = 5;
        }

        public ActionResult CerrarSesion()
        {
            HttpContext.Session.Clear();
            HttpContext.Session.Abandon();
            return RedirectToAction("Login");
        }
    }
}
