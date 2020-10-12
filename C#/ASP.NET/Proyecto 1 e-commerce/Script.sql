Create database ParcialTorresKanashiro
GO
USE [ParcialTorresKanashiro]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 11/17/2019 23:55:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellido] [varchar](50) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[clave] [varchar](50) NOT NULL,
	[esAdmin] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 11/17/2019 23:55:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedor](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[direccion] [varchar](50) NOT NULL,
	[nacionalidad] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 11/17/2019 23:55:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Producto](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[editorial] [varchar](50) NOT NULL,
	[precio] [decimal](10, 2) NOT NULL,
	[cantidad] [int] NOT NULL,
	[foto] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Compra]    Script Date: 11/17/2019 23:55:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Compra](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_usuario] [int] NOT NULL,
	[id_producto] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
	[monto_total] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Default [DF__Usuario__esAdmin__014935CB]    Script Date: 11/17/2019 23:55:06 ******/
ALTER TABLE [dbo].[Usuario] ADD  DEFAULT ((0)) FOR [esAdmin]
GO
/****** Object:  ForeignKey [FK__Compra__id_produ__0AD2A005]    Script Date: 11/17/2019 23:55:06 ******/
ALTER TABLE [dbo].[Compra]  WITH CHECK ADD FOREIGN KEY([id_producto])
REFERENCES [dbo].[Producto] ([id])
GO
/****** Object:  ForeignKey [FK__Compra__id_usuar__09DE7BCC]    Script Date: 11/17/2019 23:55:06 ******/
ALTER TABLE [dbo].[Compra]  WITH CHECK ADD FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id])
GO

insert into Producto (nombre, editorial, precio, cantidad, foto)
				values ('Manga','Ivrea',400, 10, '45354535.png')
insert into Producto (nombre, editorial, precio, cantidad, foto)
				values ('Libro','Santillan',350, 15, '3453454.jpg')
insert into Producto (nombre, editorial, precio, cantidad, foto)
				values ('Revista','Gente',125.5, 19, '423424.jpg')
insert into Producto (nombre, editorial, precio, cantidad, foto)
				values ('Comic','Marvel',300, 40, '1366_2000.jpg')
insert into Usuario (nombre, apellido, email, clave, esAdmin)
				values ('admin','admin','admin@admin','admin',1)
insert into Usuario (nombre, apellido, email, clave, esAdmin)
				values ('max','max','max@max','max',0)
insert into Proveedor (nombre, direccion, nacionalidad)
				values ('Santillana','Juncal 521, CABA','Argentina')
insert into Proveedor (nombre, direccion, nacionalidad)
				values ('Ivrea','Suipacha 1756, CABA','Argentina')		