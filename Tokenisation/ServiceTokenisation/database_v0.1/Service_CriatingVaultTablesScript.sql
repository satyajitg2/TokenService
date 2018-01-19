CREATE DATABASE [TOK_MCO]

/****** Object:  Table [dbo].[TokenDateTime]    Script Date: 12/15/2015 2:44:35 PM ******/
USE [TOK_MCO]
GO

CREATE TABLE [dbo].[TokenDateTime](
	[Token] [datetime] NOT NULL,
	[DomainIdentifier] [int] NULL,
	[BusinessEntityIdentifier] [int] NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NULL,
	[CSDValue] [varchar](255) NULL,
	[SystemIdentifier] [int] NULL,
	[SourceFieldName] [varchar](30) NULL,
	[TokenMetadata] [varchar](255) NULL,
	[TargetFieldName] [varchar](30) NULL,
 CONSTRAINT [pk_TokenDateTimeIdentifier] PRIMARY KEY CLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/****** Object:  Table [dbo].[TokenInteger]    Script Date: 12/15/2015 2:44:46 PM ******/

CREATE TABLE [dbo].[TokenInteger](
	[Token] [bigint] NOT NULL,
	[DomainIdentifier] [int] NULL,
	[BusinessEntityIdentifier] [int] NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NULL,
	[CSDValue] [varchar](255) NULL,
	[SystemIdentifier] [int] NULL,
	[SourceFieldName] [varchar](30) NULL,
	[TokenMetadata] [varchar](255) NULL,
	[TargetFieldName] [varchar](30) NULL,
 CONSTRAINT [pk_TokenIntegerIdentifier] PRIMARY KEY CLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/****** Object:  Table [dbo].[TokenString]    Script Date: 12/15/2015 2:44:59 PM ******/


CREATE TABLE [dbo].[TokenString](
	[Token] [varchar](10) NOT NULL,
	[DomainIdentifier] [int] NULL,
	[BusinessEntityIdentifier] [int] NULL,
	[RepeatableFlag] [bit] NULL,
	[TokenExpiryDate] [datetime] NULL,
	[TokenCreationDateTime] [datetime] NULL,
	[CSDValue] [varchar](255) NULL,
	[SystemIdentifier] [int] NULL,
	[SourceFieldName] [varchar](30) NULL,
	[TokenMetadata] [varchar](255) NULL,
	[TargetFieldName] [varchar](30) NULL,
 CONSTRAINT [pk_TokenStringIdentifier] PRIMARY KEY CLUSTERED 
(
	[Token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


