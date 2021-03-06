USE [master]
GO
/****** Object:  Database [TOK_VAULT]    Script Date: 3/4/2016 10:53:20 AM ******/
CREATE DATABASE [TOK_VAULT]

USE [TOK_VAULT]
GO
/****** Object:  Table [dbo].[TokenDateTime]    Script Date: 3/4/2016 10:53:20 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TokenDateTime](
	[Token] [datetime] NOT NULL,
	[DomainName] [varchar](255) NULL,
	[SystemName] [varchar](255) NULL,
	[BusinessEntityName] [varchar](255) NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NULL,
	[CSDValue] [varchar](255) NULL,
	[SourceFieldName] [varchar](30) NULL,
	[TargetFieldName] [varchar](30) NULL,
	[TokenMetadata] [varchar](255) NULL,
 CONSTRAINT [pk_TokenDateTimeIdentifier] PRIMARY KEY CLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TokenInteger]    Script Date: 3/4/2016 10:53:20 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TokenInteger](
	[Token] [bigint] NOT NULL,
	[DomainName] [varchar](255) NULL,
	[SystemName] [varchar](255) NULL,
	[BusinessEntityName] [varchar](255) NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NULL,
	[CSDValue] [varchar](255) NULL,
	[SourceFieldName] [varchar](30) NULL,
	[TargetFieldName] [varchar](30) NULL,
	[TokenMetadata] [varchar](255) NULL,
 CONSTRAINT [pk_TokenIntegerIdentifier] PRIMARY KEY CLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TokenString]    Script Date: 3/4/2016 10:53:20 AM ******/

Drop table [TOK_VAULT].[dbo].[TokenString]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TOK_VAULT].[dbo].[TokenString](
[Token] [varchar](10) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	[DomainName] [varchar](255) NULL,
	[SystemName] [varchar](255) NULL,
	[BusinessEntityName] [varchar](255) NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NULL,
	[CSDValue] [varchar](255) NULL,
	[SourceFieldName] [varchar](30) NULL,
	[TargetFieldName] [varchar](30) NULL,
	[TokenMetadata] [varchar](255) NULL,
 CONSTRAINT [pk_TokenStringIdentifier] PRIMARY KEY CLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
USE [master]
GO
ALTER DATABASE [TOK_VAULT] SET  READ_WRITE 
GO
