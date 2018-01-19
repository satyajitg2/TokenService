/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
 * Script to create the [Vault] Schema on database instance <VAULT_DATABASE>
 *
 * 
 * <VAULT_DATABASE> is expected as it will be created by BARCLAYS Infrastructure team.
 */

USE [TOKEN_GVA_SIT]
GO


/*
 *
 * Checks if vault schema exists, else tries to create
 * The Create Schema works with DB_OWNER access in Barclays
 */

IF OBJECT_ID('vault') IS NOT NULL
    PRINT '<<< vault Schema available >>>'
ELSE
    CREATE SCHEMA [Vault]
go

--/****** Object:  Schema [Vault]   ******/


IF OBJECT_ID('vault.TokenDateTime', 'U') IS NOT NULL 
  DROP TABLE vault.TokenDateTime; 

USE [TOKEN_GVA_SIT]
GO

CREATE TABLE [Vault].[TokenDateTime](
	[Token] [datetime] NOT NULL,
	[DomainName] [varchar](255) NULL,
	[SystemName] [varchar](255) NULL,
	[BusinessEntityName] [varchar](255) NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NOT NULL,
	[CSDValue] [varchar](4000) NULL,	
	[SourceFieldName] [varchar](250) NULL,
	[TokenMetadata] [varchar](4000) NULL,
	[TargetFieldName] [varchar](250) NULL,
 CONSTRAINT [pk_TokenDateTimeIdentifier] PRIMARY KEY NONCLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) 
ON [PRIMARY]
--ON PartitionBymonth (TokenCreationDateTime); 
)
ON PartitionBymonth (TokenCreationDateTime);  ---This will only run if *_Partition.sql executes without issues

CREATE CLUSTERED INDEX  CX_TokenDateTime ON [Vault].[TokenDateTime] ([TokenCreationDateTime])  ON PartitionBymonth (TokenCreationDateTime); 


/****** Object:  Table [Vault].[TokenInteger]    Script Date: 12/15/2015 2:44:46 PM ******/
IF OBJECT_ID('vault.TokenInteger', 'U') IS NOT NULL 
  DROP TABLE [Vault].[TokenInteger];

 USE [TOKEN_GVA_SIT]
GO
CREATE TABLE [Vault].[TokenInteger](
	[Token] [bigint] NOT NULL,
	[DomainName] [varchar](255) NULL,
	[SystemName] [varchar](255) NULL,
	[BusinessEntityName] [varchar](255) NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NOT NULL,
	[CSDValue] [varchar](4000) NULL,	
	[SourceFieldName] [varchar](250) NULL,
	[TokenMetadata] [varchar](4000) NULL,
	[TargetFieldName] [varchar](250) NULL,
 CONSTRAINT [pk_TokenIntegerIdentifier] PRIMARY KEY NONCLUSTERED
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) 
ON [PRIMARY]
--ON PartitionBymonth (TokenCreationDateTime); 
)
 ON PartitionBymonth (TokenCreationDateTime); ---This will only run if *_partion.sql executes without issues


CREATE CLUSTERED INDEX  CX_TokenInteger ON [Vault].[TokenInteger] ([TokenCreationDateTime])  ON PartitionBymonth (TokenCreationDateTime); 
 
/****** Object:  Table [Vault].[TokenString]    Script Date: 12/15/2015 2:44:59 PM ******/

IF OBJECT_ID('vault.TokenString', 'U') IS NOT NULL 
  DROP TABLE [Vault].[TokenString];

  USE [TOKEN_GVA_SIT]
GO
CREATE TABLE [Vault].[TokenString](
	[Token] [varchar](10) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	[DomainName] [varchar](255) NULL,
	[SystemName] [varchar](255) NULL,
	[BusinessEntityName] [varchar](255) NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NOT NULL,
	[CSDValue] [varchar](4000) NULL,	
	[SourceFieldName] [varchar](250) NULL,
	[TokenMetadata] [varchar](4000) NULL,
	[TargetFieldName] [varchar](250) NULL,
 CONSTRAINT [pk_TokenStringIdentifier] PRIMARY KEY NONCLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) 
ON [PRIMARY]
--ON PartitionBymonth (TokenCreationDateTime); 
)
 
 ON PartitionBymonth (TokenCreationDateTime); ---This will only run if *_vault_partion.sql executes without issues

CREATE CLUSTERED INDEX  CX_TokenString ON [Vault].[TokenString] ([TokenCreationDateTime])  ON PartitionBymonth (TokenCreationDateTime); 


