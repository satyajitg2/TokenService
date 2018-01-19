/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
  *
 * Database Creation Script. (Use if required.)
 * The <FILENAME> is to be defined by Barclays Infrastructure team.

 **** NOTE:
 **** Before Executing - The user need to replace TOKEN_GVA_SIT with corresponding <DATABASE_INSTANCE_NAME>
 */

USE [master]
GO

/****** Object:  Database [TOKEN_GVA_SIT]    Script Date: 2/9/2016 6:34:28 PM ******/
----NOTE: C:\Program Files\Microsoft SQL Server\...is on local DEV dB. Need to change to point to DB Installation
CREATE DATABASE [TOKEN_GVA_SIT] ON  PRIMARY 
( NAME = N'TOKEN_GVA_SIT', FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT.mdf' , SIZE = 2304KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TOKEN_GVA_SIT_log', FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\TOKEN_GVA_SIT_log.LDF' , SIZE = 576KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET COMPATIBILITY_LEVEL = 100
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TOKEN_GVA_SIT].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

/*********** ALTER script to run on DATABASE Instance ********/

ALTER DATABASE [TOKEN_GVA_SIT] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET ARITHABORT OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET AUTO_CLOSE ON 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET  ENABLE_BROKER 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET  MULTI_USER 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET DB_CHAINING OFF 
GO

ALTER DATABASE [TOKEN_GVA_SIT] SET  READ_WRITE 
GO