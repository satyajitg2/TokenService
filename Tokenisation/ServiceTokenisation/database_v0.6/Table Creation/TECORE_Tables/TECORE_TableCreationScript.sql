USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[APIAUTH]    Script Date: 5/2/2016 10:49:37 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [TECORE].[APIAUTH](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[SYSTEMNAME] [varchar](100) NOT NULL,
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[Configuration]    Script Date: 5/2/2016 10:50:00 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [TECORE].[Configuration](
	[ConfigurationIdentifier] [int] IDENTITY(1,1) NOT NULL,
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[MonitoringJobs]    Script Date: 5/2/2016 10:50:12 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [TECORE].[MonitoringJobs](
	[M_ID] [int] NOT NULL,
	[M_Type] [varchar](50) NOT NULL,
	[M_Name] [varchar](255) NOT NULL,
	[M_Script_Type] [varchar](100) NOT NULL,
	[M_Script] [varchar](max) NOT NULL,
	[M_Start_Pattern] [varchar](25) NOT NULL,
	[M_frequency_Inmins] [bigint] NULL,
	[M_LastReportTime] [datetime] NULL,
	[M_LastReportedValue] [varchar](500) NULL,
	[Is_Enabled] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[M_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedDateTime1]    Script Date: 5/2/2016 10:50:21 AM ******/
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedDateTime2]    Script Date: 5/2/2016 10:50:33 AM ******/
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedDateTime3]    Script Date: 5/2/2016 10:50:45 AM ******/
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedInteger1]    Script Date: 5/2/2016 10:50:55 AM ******/
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedInteger2]    Script Date: 5/2/2016 10:51:06 AM ******/
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedInteger3]    Script Date: 5/2/2016 10:51:20 AM ******/
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedString1]    Script Date: 5/2/2016 10:59:16 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [TECORE].[SeedString1](
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedString2]    Script Date: 5/2/2016 11:00:18 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [TECORE].[SeedString2](
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[SeedString3]    Script Date: 5/2/2016 11:00:32 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
GO

CREATE TABLE [TECORE].[SeedString3](
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[Table_Set]    Script Date: 5/2/2016 11:00:43 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
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


USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[TokenDecision]    Script Date: 5/2/2016 11:00:52 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING OFF
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
	[UpdatedDateTime] [datetime] NULL,
	[UpdateCount] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [TOKEN_GVA_SIT]
GO