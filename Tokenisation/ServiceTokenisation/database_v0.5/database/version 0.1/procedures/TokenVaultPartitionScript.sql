/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
  *
 * DB Partioning extension script
 */

USE [TOKEN_GVA_SIT]
GO

/*********** ALTER script to run on for Partioning ********/
---
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP January
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP February
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP March
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP April
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP May
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP June
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP July
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP August
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP September
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP October
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP November
GO
ALTER DATABASE [TOKEN_GVA_SIT]
ADD FILEGROUP December
GO


/*********** ALTER script to add .ndf file entries Calendar Month wise. ********/
--add .ndf file to every filegroup:
USE [TOKEN_GVA_SIT]
GO

ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartJan],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_JAN.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [January];


	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartFeb],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_FEB.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [February];

	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartMar],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_MAR.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [March];

	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartApr],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_APR.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [April];

	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartMay],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_MAY.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [May];


	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartJun],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_JUN.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [June];


	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartJul],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_JUL.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [July];


	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartAug],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_AUG.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [August];


	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartSep],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_SEP.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [September];

	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartOct],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_OCT.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [October];

	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartNov],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_NOV.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [November];

	ALTER DATABASE [TOKEN_GVA_SIT]
    ADD FILE
    (
    NAME = [PartDec],
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_DEC.ndf',
        SIZE = 3072 KB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1024 KB
    ) TO FILEGROUP [December];