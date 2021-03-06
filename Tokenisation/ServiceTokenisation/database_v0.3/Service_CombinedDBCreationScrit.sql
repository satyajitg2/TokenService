USE [master]
GO
/****** Object:  Database [Tokenisation]    Script Date: 3/4/2016 10:13:28 AM ******/
CREATE DATABASE [Tokenisation]
 
USE [Tokenisation]
GO
/****** Object:  Table [dbo].[APIAUTH]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[APIAUTH](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[SYSTEMNAME] [varchar](10) NOT NULL,
	[BUSINESSENTITY] [varchar](10) NOT NULL,
	[DOMAIN] [varchar](10) NOT NULL,
	[APINAME] [varchar](10) NOT NULL,
 CONSTRAINT [pk_APIAUTHorization] PRIMARY KEY CLUSTERED 
(
	[SYSTEMNAME] ASC,
	[BUSINESSENTITY] ASC,
	[DOMAIN] ASC,
	[APINAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Configuration]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Configuration](
	[ConfigurationIdentifier] [int] NOT NULL,
	[StringVaultIdentifer] [varchar](50) NULL,
	[IntegerTokenStartValue] [bigint] NULL,
	[IntegerTokenEndValue] [bigint] NULL,
	[DateTimeTokenYearStart] [int] NULL,
	[DateTimeTokenYearEnd] [int] NULL,
	[BusinessEntityName] [varchar](255) NULL,
 CONSTRAINT [pk_ConfigurationIdentifier] PRIMARY KEY CLUSTERED 
(
	[ConfigurationIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SeedDateTime1]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeedDateTime1](
	[Seed] [int] NOT NULL,
	[SeedValue] [datetime] NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk1_SeedDT] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SeedDateTime2]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeedDateTime2](
	[Seed] [int] NOT NULL,
	[SeedValue] [datetime] NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk2_SeedDT] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SeedDateTime3]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeedDateTime3](
	[Seed] [int] NOT NULL,
	[SeedValue] [datetime] NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk3_SeedDT] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SeedInteger1]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeedInteger1](
	[Seed] [int] NOT NULL,
	[SeedValue] [bigint] NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk1_SeedInt] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SeedInteger2]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeedInteger2](
	[Seed] [int] NOT NULL,
	[SeedValue] [bigint] NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk2_SeedInt] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SeedInteger3]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeedInteger3](
	[Seed] [int] NOT NULL,
	[SeedValue] [bigint] NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk3_SeedInt] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SeedString1]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SeedString1](
	[Seed] [int] NOT NULL,
	[SeedValue] [varchar](10) NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk1_SeedLStr] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SeedString2]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SeedString2](
	[Seed] [int] NOT NULL,
	[SeedValue] [varchar](10) NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk2_SeedLStr] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SeedString3]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SeedString3](
	[Seed] [int] NOT NULL,
	[SeedValue] [varchar](10) NOT NULL,
	[SeedUsedFlag] [bit] NOT NULL,
	[Vault] [int] NULL,
 CONSTRAINT [pk3_SeedLStr] PRIMARY KEY CLUSTERED 
(
	[Seed] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Table_Set]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Table_Set](
	[TokenSet] [int] NOT NULL,
	[TokenIntegerTable] [varchar](50) NOT NULL,
	[TokenStringValueTable] [varchar](50) NOT NULL,
	[TokenDateTimeValueTable] [varchar](50) NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TokenDecision]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TokenDecision](
	[Id] [int] NOT NULL,
	[TableSetToUseByTokenSeeder] [int] NOT NULL,
	[TableSetToUseByTokenEngine] [int] NOT NULL,
	[TokenToStartGenerateInteger] [bigint] NOT NULL,
	[TokenToStartGenerateString] [varchar](7) NOT NULL,
	[TokenToStartGenerateDateTime] [datetime] NOT NULL,
	[MaxTokenToGenerateInteger] [int] NOT NULL,
	[MaxTokenToGenerateString] [int] NOT NULL,
	[MaxTokenToGenerateDateTime] [int] NOT NULL,
	[ConfigurationIdentifier] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[TokenDecision]  WITH CHECK ADD FOREIGN KEY([ConfigurationIdentifier])
REFERENCES [dbo].[Configuration] ([ConfigurationIdentifier])
GO
/****** Object:  StoredProcedure [dbo].[SP_CHECKAUTHORISATION]    Script Date: 3/4/2016 10:13:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE PROCEDURE [dbo].[SP_CHECKAUTHORISATION] @System varchar(10), @BusinessEntity varchar(10), 
@Domain varchar(10), @APIName varchar(10), @IsAuthorized varchar(20) OUTPUT
AS
SELECT @IsAuthorized = 
CASE  count(1)
 when 1 THEN 'AUTHORISED'
 ELSE 'NOT AUTHORISED'
 END
FROM [Tokenisation].[dbo].[APIAUTH] 
WHERE SYSTEMNAME = @System
AND BUSINESSENTITY = @BusinessEntity
AND DOMAIN = @Domain
AND APINAME = @APIName;

GO
USE [master]
GO
ALTER DATABASE [Tokenisation] SET  READ_WRITE 
GO
