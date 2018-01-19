
/****** Object:  Table [Vault].[TokenDateTime]    Script Date: 5/2/2016 11:01:01 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [Vault].[TokenInteger]    Script Date: 5/2/2016 11:01:11 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [Vault].[TokenString]    Script Date: 5/2/2016 11:01:21 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [Vault].[TokenString](
	[Token] [varchar](10) NOT NULL,
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


