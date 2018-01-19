/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
 * Script to DROP the Foreign and Primary Key Constraints from [TOKEN_GVA_SIT]
  */

/*DROP FOREIGN KEY CONSTRAINTS*//

/***** APIAccess table *******/

USE [TOKEN_GVA_SIT]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[TECORE].[fk_APIIdentifier]') AND parent_object_id = OBJECT_ID(N'[TECORE].[APIAccess]'))
ALTER TABLE [TECORE].[APIAccess] DROP CONSTRAINT [fk_APIIdentifier]
GO


IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[TECORE].[fk_SystemIdentifier]') AND parent_object_id = OBJECT_ID(N'[TECORE].[APIAccess]'))
ALTER TABLE [TECORE].[APIAccess] DROP CONSTRAINT [fk_SystemIdentifier]
GO


/* Configuration table */
USE [TOKEN_GVA_SIT]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[TECORE].[fk_Configuration_BusinessEntityIdentifier]') AND parent_object_id = OBJECT_ID(N'[TECORE].[Configuration]'))
ALTER TABLE [TECORE].[Configuration] DROP CONSTRAINT [fk_Configuration_BusinessEntityIdentifier]
GO


/*BusinessEntity table */

USE [TOKEN_GVA_SIT]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[TECORE].[fk_APIAccessIdentifier]') AND parent_object_id = OBJECT_ID(N'[TECORE].[BusinessEntity]'))
ALTER TABLE [TECORE].[BusinessEntity] DROP CONSTRAINT [fk_APIAccessIdentifier]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[TECORE].[fk_BusinessEntity_TokenVaultIdentifier]') AND parent_object_id = OBJECT_ID(N'[TECORE].[BusinessEntity]'))
ALTER TABLE [TECORE].[BusinessEntity] DROP CONSTRAINT [fk_BusinessEntity_TokenVaultIdentifier]
GO



/* System table*/

USE [TOKEN_GVA_SIT]
GO

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[TECORE].[fk_DomainIdentifier]') AND parent_object_id = OBJECT_ID(N'[TECORE].[System]'))
ALTER TABLE [TECORE].[System] DROP CONSTRAINT [fk_DomainIdentifier]
GO




/******************************** DROP PRIMARY Key Constraint *****************************/

/* API table */

USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_APIIdentifier]    Script Date: 02/19/2016 12:06:05 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[API]') AND name = N'pk_APIIdentifier')
ALTER TABLE [TECORE].[API] DROP CONSTRAINT [pk_APIIdentifier]
GO

/* APIAccess table */

USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_APIAccessIdentifier]    Script Date: 02/19/2016 12:06:56 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[APIAccess]') AND name = N'pk_APIAccessIdentifier')
ALTER TABLE [TECORE].[APIAccess] DROP CONSTRAINT [pk_APIAccessIdentifier]
GO

/* BusinessEntity table */
USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_BusinessEntityIdentifier]    Script Date: 02/19/2016 12:07:23 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[BusinessEntity]') AND name = N'pk_BusinessEntityIdentifier')
ALTER TABLE [TECORE].[BusinessEntity] DROP CONSTRAINT [pk_BusinessEntityIdentifier]
GO

/* Configuration table */

USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_ConfigurationIdentifier]    Script Date: 02/19/2016 12:08:00 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[Configuration]') AND name = N'pk_ConfigurationIdentifier')
ALTER TABLE [TECORE].[Configuration] DROP CONSTRAINT [pk_ConfigurationIdentifier]
GO

/* Domain table */
USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_DomainIdentifier]    Script Date: 02/19/2016 12:14:38 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[Domain]') AND name = N'pk_DomainIdentifier')
ALTER TABLE [TECORE].[Domain] DROP CONSTRAINT [pk_DomainIdentifier]
GO


/*SYSTEM table */
USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_SystemIdentifier]    Script Date: 02/19/2016 12:16:56 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[System]') AND name = N'pk_SystemIdentifier')
ALTER TABLE [TECORE].[System] DROP CONSTRAINT [pk_SystemIdentifier]
GO

/* TokenVault table */

USE [TOKEN_GVA_SIT]
GO

/****** Object:  Index [pk_TokenVaultIdentifier]    Script Date: 02/19/2016 12:18:11 ******/
IF  EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[TECORE].[TokenVault]') AND name = N'pk_TokenVaultIdentifier')
ALTER TABLE [TECORE].[TokenVault] DROP CONSTRAINT [pk_TokenVaultIdentifier]
GO
