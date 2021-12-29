USE [master]
GO
/****** Object:  Database [Lab2]    Script Date: 12/8/2021 6:07:48 PM ******/
CREATE DATABASE [Lab2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Lab2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\Lab2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Lab2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\Lab2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Lab2] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Lab2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Lab2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Lab2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Lab2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Lab2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Lab2] SET ARITHABORT OFF 
GO
ALTER DATABASE [Lab2] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Lab2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Lab2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Lab2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Lab2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Lab2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Lab2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Lab2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Lab2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Lab2] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Lab2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Lab2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Lab2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Lab2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Lab2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Lab2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Lab2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Lab2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Lab2] SET  MULTI_USER 
GO
ALTER DATABASE [Lab2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Lab2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Lab2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Lab2] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Lab2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Lab2] SET QUERY_STORE = OFF
GO
USE [Lab2]
GO
/****** Object:  Table [dbo].[tblHotel]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblHotel](
	[hotelID] [nvarchar](50) NOT NULL,
	[hotelName] [nvarchar](50) NULL,
	[hotelAddress] [nvarchar](250) NULL,
	[hotelCity] [nvarchar](50) NULL,
	[hotelPhoneNumber] [nchar](10) NULL,
	[hotelImg] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblHotel] PRIMARY KEY CLUSTERED 
(
	[hotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [nvarchar](50) NOT NULL,
	[emailUser] [nvarchar](50) NOT NULL,
	[orderTotal] [float] NULL,
	[orderStatus] [bit] NULL,
	[orderDateCreate] [date] NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [nvarchar](50) NOT NULL,
	[roomID] [int] NOT NULL,
	[checkInDate] [date] NULL,
	[checkOutDate] [date] NULL,
	[total] [float] NULL,
	[quantity] [int] NULL,
	[orderDetailStatus] [bit] NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPromotion]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPromotion](
	[promotionCode] [nvarchar](50) NOT NULL,
	[promotionDateExpired] [date] NULL,
	[promotionStatus] [bit] NULL,
	[promotionValue] [float] NULL,
 CONSTRAINT [PK_tblPromotion] PRIMARY KEY CLUSTERED 
(
	[promotionCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPromotionDetail]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPromotionDetail](
	[promotionDetailID] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](50) NULL,
	[promotionCode] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblPromotionDetail] PRIMARY KEY CLUSTERED 
(
	[promotionDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoom]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoom](
	[roomID] [int] IDENTITY(1,1) NOT NULL,
	[hotelID] [nvarchar](50) NOT NULL,
	[roomDescription] [nvarchar](250) NULL,
	[roomTypeID] [nvarchar](50) NOT NULL,
	[roomStatus] [bit] NULL,
	[roomPrice] [float] NULL,
	[checkInDate] [date] NULL,
	[checkOutDate] [date] NULL,
	[roomImg] [nvarchar](50) NULL,
	[roomQuantity] [int] NULL,
 CONSTRAINT [PK_tblRoom] PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoomType]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoomType](
	[roomTypeID] [nvarchar](50) NOT NULL,
	[roomType] [nvarchar](50) NULL,
	[rtDescription] [nvarchar](100) NULL,
 CONSTRAINT [PK_tblRoomType] PRIMARY KEY CLUSTERED 
(
	[roomTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSenderEmail]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSenderEmail](
	[senderEmail] [nvarchar](50) NULL,
	[senderPassword] [nvarchar](50) NULL,
	[senderStatus] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 12/8/2021 6:07:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
	[username] [nvarchar](50) NULL,
	[phone] [nchar](10) NULL,
	[address] [nvarchar](50) NULL,
	[role] [nvarchar](50) NULL,
	[status] [bit] NULL,
	[createDate] [date] NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([emailUser])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblRoom] FOREIGN KEY([roomID])
REFERENCES [dbo].[tblRoom] ([roomID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblRoom]
GO
ALTER TABLE [dbo].[tblPromotionDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblPromotionDetail_tblPromotion] FOREIGN KEY([promotionCode])
REFERENCES [dbo].[tblPromotion] ([promotionCode])
GO
ALTER TABLE [dbo].[tblPromotionDetail] CHECK CONSTRAINT [FK_tblPromotionDetail_tblPromotion]
GO
ALTER TABLE [dbo].[tblPromotionDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblPromotionDetail_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblPromotionDetail] CHECK CONSTRAINT [FK_tblPromotionDetail_tblUser]
GO
ALTER TABLE [dbo].[tblRoom]  WITH CHECK ADD  CONSTRAINT [FK_tblRoom_tblHotel] FOREIGN KEY([hotelID])
REFERENCES [dbo].[tblHotel] ([hotelID])
GO
ALTER TABLE [dbo].[tblRoom] CHECK CONSTRAINT [FK_tblRoom_tblHotel]
GO
ALTER TABLE [dbo].[tblRoom]  WITH CHECK ADD  CONSTRAINT [FK_tblRoom_tblRoomType] FOREIGN KEY([roomTypeID])
REFERENCES [dbo].[tblRoomType] ([roomTypeID])
GO
ALTER TABLE [dbo].[tblRoom] CHECK CONSTRAINT [FK_tblRoom_tblRoomType]
GO
USE [master]
GO
ALTER DATABASE [Lab2] SET  READ_WRITE 
GO
