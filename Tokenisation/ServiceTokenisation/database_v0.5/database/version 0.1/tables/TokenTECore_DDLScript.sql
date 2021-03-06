/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
 * Script to create the [TECORE] Schema on database instance [TOKEN_GVA_SIT]
 *
 * If TECORE schema exists script will create/alter the objects such as TABLES
 * TOKEN_GVA_SIT is expected as this will be created by BARCLAYS Infrastructure.
 */

USE [TOKEN_GVA_SIT]
GO


ALTER DATABASE [TOKEN_GVA_SIT] COLLATE SQL_Latin1_General_CP1_CS_AS
GO


/*
 *
 * Checks if TECORE schema exists, else tries to create
 * The Create Schema works with DB_OWNER access in Barclays
 */

--IF OBJECT_ID('TECORE') IS NOT NULL
--    PRINT '<<< TECORE Schema available >>>'
--ELSE
--    CREATE SCHEMA [TECORE]
go

/*
 * Creates the Tokenisation Engine Core TABLES from this line onward.
*/
--Removed following table from TECORE on March 7th. They are not required for TE to run.
-- API
-- APIACCESS
-- BusinessEntity
-- Domain
-- TokenVault
-- System

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[Configuration]') AND type in (N'U'))
DROP TABLE [TECORE].[Configuration]
GO
/****** Object:  Table [TECORE].[Configuration]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TECORE].[Configuration](
	[ConfigurationIdentifier] [int] NOT NULL IDENTITY(1,1),
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



/****** Object:  Table [TECORE].[SeedDateTime1]     ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedDateTime1]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedDateTime1]
GO
	
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [TECORE].[SeedDateTime1](
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
/****** Object:  Table [TECORE].[SeedDateTime2]   ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedDateTime2]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedDateTime2]
GO
	
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [TECORE].[SeedDateTime2](
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
/****** Object:  Table [TECORE].[SeedDateTime3]    ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedDateTime3]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedDateTime3]
GO
	
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [TECORE].[SeedDateTime3](
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
/****** Object:  Table [TECORE].[SeedInteger1]     ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedInteger1]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedInteger1]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [TECORE].[SeedInteger1](
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
/****** Object:  Table [TECORE].[SeedInteger2]     ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedInteger2]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedInteger2]
GO
	
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [TECORE].[SeedInteger2](
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
/****** Object:  Table [TECORE].[SeedInteger3]     ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedInteger3]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedInteger3]
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [TECORE].[SeedInteger3](
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
/****** Object:  Table [TECORE].[SeedString1]     ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedString1]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedString1]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TECORE].[SeedString1](
	[Seed] [int] NOT NULL,
	[SeedValue] [varchar](10) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
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
/****** Object:  Table [TECORE].[SeedString2]    ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedString2]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedString2]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TECORE].[SeedString2](
	[Seed] [int] NOT NULL,
	[SeedValue] [varchar](10) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
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
/****** Object:  Table [TECORE].[SeedString3]     ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[SeedString3]') AND type in (N'U'))
DROP TABLE [TECORE].[SeedString3]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TECORE].[SeedString3](
	[Seed] [int] NOT NULL,
	[SeedValue] [varchar](10) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
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
/****** Object:  Table [TECORE].[System]     ******/


/****** Object:  Table [TECORE].[Table_Set]     ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[Table_Set]') AND type in (N'U'))
DROP TABLE [TECORE].[Table_Set]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TECORE].[Table_Set](
	[TokenSet] [int] NOT NULL,
	[TokenIntegerTable] [varchar](50) NOT NULL,
	[TokenStringValueTable] [varchar](50) NOT NULL,
	[TokenDateTimeValueTable] [varchar](50) NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [TECORE].[TokenDecision]     ******/

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[TokenDecision]') AND type in (N'U'))
DROP TABLE [TECORE].[TokenDecision]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [TECORE].[TokenDecision](
	[Id] [int] NOT NULL,
	[TableSetToUseByTokenSeeder] [int] NOT NULL,
	[TableSetToUseByTokenEngine] [int] NOT NULL,
	[TokenToStartGenerateInteger] [bigint] NOT NULL,
	[TokenToStartGenerateString] [varchar](7) NOT NULL,
	[TokenToStartGenerateDateTime] [datetime] NOT NULL,
	[MaxTokenToGenerateInteger] [int] NOT NULL,
	[MaxTokenToGenerateString] [int] NOT NULL,
	[MaxTokenToGenerateDateTime] [int] NOT NULL,
	[MinTokenInInteger] [int] NOT NULL,
	[MinTokenInString] [int] NOT NULL,
	[MinTokenInDateTime] [int] NOT NULL,
	[Token_Rotation_Freq] [int] NOT NULL,
	[Next_Rotation_time] [datetime] NOT NULL,
	[ConfigurationIdentifier] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/****** Object:  Table [TECORE].[APIAUTH]    ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[TECORE].[APIAUTH]') AND type in (N'U'))
DROP TABLE [TECORE].[APIAUTH]
GO


IF OBJECT_ID('TECORE.APIAUTH', 'U') IS NOT NULL 
  DROP TABLE TECORE.APIAUTH; 
GO
--Create new table - APIAuth
CREATE TABLE [TOKEN_GVA_SIT].[TECORE].[APIAUTH](
	[ID] [int] NOT NULL IDENTITY(1,1),
	[SYSTEMNAME] [varchar](100) NOT NULL,	
	[BUSINESSENTITY] [varchar](10) NOT NULL,
	[DOMAIN] [varchar](10) NOT NULL,
	[APINAME] [varchar](10) NOT NULL
 CONSTRAINT [pk_APIAUTHorization] PRIMARY KEY 
(
	[SYSTEMNAME],
	[BUSINESSENTITY],
	[DOMAIN],
	[APINAME]
)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


IF OBJECT_ID('TECORE.MonitoringJobs', 'U') IS NOT NULL 
  DROP TABLE TECORE.MonitoringJobs; 
GO
--Create new table - MonitoringJobs
CREATE TABLE [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
(
M_ID int NOT NULL PRIMARY KEY,
M_Type varchar(50) NOT NULL , 
M_Name varchar(255) NOT NULL, 
M_Script_Type varchar(100) NOT NULL, 
M_Script varchar(max) NOT NULL, 
M_Start_Pattern varchar(25) NOT NULL, 
M_frequency_Inmins  Bigint,
M_LastReportTime datetime, 
M_LastReportedValue varchar(500) , 
Is_Enabled bit NOT NULL
)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



-- ******* All constraints are removed from March 7th 2016 ******* --

/****** ALTER Scripts on Core tables ******/
/*
GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [TECORE].[APIAccess]  WITH CHECK ADD CONSTRAINT [fk_APIIdentifier] FOREIGN KEY([APIIdentifier])
REFERENCES [TECORE].[API] ([APIIdentifier])
GO
ALTER TABLE [TECORE].[APIAccess]  WITH CHECK ADD CONSTRAINT [fk_SystemIdentifier] FOREIGN KEY([SystemIdentifier])
REFERENCES [TECORE].[System] ([SystemIdentifier])
GO
ALTER TABLE [TECORE].[BusinessEntity]  WITH CHECK ADD CONSTRAINT [fk_APIAccessIdentifier] FOREIGN KEY([APIAccessIdentifier])
REFERENCES [TECORE].[APIAccess] ([APIAccessIdentifier])
GO
ALTER TABLE [TECORE].[BusinessEntity] CHECK CONSTRAINT [fk_APIAccessIdentifier]
GO
ALTER TABLE [TECORE].[BusinessEntity]  WITH CHECK ADD  CONSTRAINT [fk_BusinessEntity_TokenVaultIdentifier] FOREIGN KEY([TokenVaultIdentifier])
REFERENCES [TECORE].[TokenVault] ([TokenVaultIdentifier])
GO
ALTER TABLE [TECORE].[BusinessEntity] CHECK CONSTRAINT [fk_BusinessEntity_TokenVaultIdentifier]
GO
ALTER TABLE [TECORE].[Configuration]  WITH CHECK ADD  CONSTRAINT [fk_Configuration_BusinessEntityIdentifier] FOREIGN KEY([BusinessEntityIdentifier])
REFERENCES [TECORE].[BusinessEntity] ([BusinessEntityIdentifier])
GO
ALTER TABLE [TECORE].[Configuration] CHECK CONSTRAINT [fk_Configuration_BusinessEntityIdentifier]
GO
ALTER TABLE [TECORE].[System]  WITH CHECK ADD CONSTRAINT[fk_DomainIdentifier]  FOREIGN KEY([DomainIdentifier])
REFERENCES [TECORE].[Domain] ([DomainIdentifier])
GO
USE [master]
GO
ALTER DATABASE [TOKEN_GVA_SIT] SET  READ_WRITE 
GO
*/