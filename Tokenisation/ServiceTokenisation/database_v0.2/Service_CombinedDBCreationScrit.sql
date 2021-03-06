CREATE DATABASE [Tokenisation]

USE [Tokenisation]
GO
/****** Object:  Table [dbo].[API]    Script Date: 1/5/2016 1:50:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[API](
	[APIIdentifier] [int] NOT NULL,
	[APIName] [varchar](250) NULL,
 CONSTRAINT [pk_APIIdentifier] PRIMARY KEY CLUSTERED 
(
	[APIIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[APIAccess]    Script Date: 1/5/2016 1:50:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[APIAccess](
	[APIAccessIdentifier] [int] NOT NULL,
	[APIIdentifier] [int] NULL,
	[SystemIdentifier] [int] NULL,
	[BusinessEntityIdentifier] [int] NULL,
	[DomainIdentifier] [int] NULL,
 CONSTRAINT [pk_APIAccessIdentifier] PRIMARY KEY CLUSTERED 
(
	[APIAccessIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BusinessEntity]    Script Date: 1/5/2016 1:50:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BusinessEntity](
	[BusinessEntityIdentifier] [int] NOT NULL,
	[TokenVaultIdentifier] [int] NULL,
	[BusinessEntityName] [varchar](30) NULL,
	[APIAccessIdentifier] [int] NULL,
 CONSTRAINT [pk_BusinessEntityIdentifier] PRIMARY KEY CLUSTERED 
(
	[BusinessEntityIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Configuration]    Script Date: 1/5/2016 1:50:34 PM ******/
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
	[BusinessEntityIdentifier] [int] NULL,
 CONSTRAINT [pk_ConfigurationIdentifier] PRIMARY KEY CLUSTERED 
(
	[ConfigurationIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Domain]    Script Date: 1/5/2016 1:50:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Domain](
	[DomainIdentifier] [int] NOT NULL,
	[DomainName] [varchar](50) NULL,
 CONSTRAINT [pk_DomainIdentifier] PRIMARY KEY CLUSTERED 
(
	[DomainIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SeedDateTime1]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedDateTime2]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedDateTime3]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedInteger1]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedInteger2]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedInteger3]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedString1]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedString2]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[SeedString3]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[System]    Script Date: 1/5/2016 1:50:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[System](
	[SystemIdentifier] [int] NOT NULL,
	[SystemName] [varchar](50) NULL,
	[DomainIdentifier] [int] NULL,
 CONSTRAINT [pk_SystemIdentifier] PRIMARY KEY CLUSTERED 
(
	[SystemIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Table_Set]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[TokenDecision]    Script Date: 1/5/2016 1:50:34 PM ******/
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
/****** Object:  Table [dbo].[TokenVault]    Script Date: 1/5/2016 1:50:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TokenVault](
	[TokenVaultIdentifier] [int] NOT NULL,
	[TokenVaultName] [varchar](20) NULL,
	[ConnectionURL] [varchar](255) NULL,
	[ConnectionUserName] [varchar](100) NULL,
	[ConnectionPassword] [varchar](100) NULL,
 CONSTRAINT [pk_TokenVaultIdentifier] PRIMARY KEY CLUSTERED 
(
	[TokenVaultIdentifier] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[APIAccess]  WITH CHECK ADD FOREIGN KEY([APIIdentifier])
REFERENCES [dbo].[API] ([APIIdentifier])
GO
ALTER TABLE [dbo].[APIAccess]  WITH CHECK ADD FOREIGN KEY([SystemIdentifier])
REFERENCES [dbo].[System] ([SystemIdentifier])
GO
ALTER TABLE [dbo].[BusinessEntity]  WITH CHECK ADD  CONSTRAINT [fk_APIAccessIdentifier] FOREIGN KEY([APIAccessIdentifier])
REFERENCES [dbo].[APIAccess] ([APIAccessIdentifier])
GO
ALTER TABLE [dbo].[BusinessEntity] CHECK CONSTRAINT [fk_APIAccessIdentifier]
GO
ALTER TABLE [dbo].[BusinessEntity]  WITH CHECK ADD  CONSTRAINT [fk_BusinessEntity_TokenVaultIdentifier] FOREIGN KEY([TokenVaultIdentifier])
REFERENCES [dbo].[TokenVault] ([TokenVaultIdentifier])
GO
ALTER TABLE [dbo].[BusinessEntity] CHECK CONSTRAINT [fk_BusinessEntity_TokenVaultIdentifier]
GO
ALTER TABLE [dbo].[Configuration]  WITH CHECK ADD  CONSTRAINT [fk_Configuration_BusinessEntityIdentifier] FOREIGN KEY([BusinessEntityIdentifier])
REFERENCES [dbo].[BusinessEntity] ([BusinessEntityIdentifier])
GO
ALTER TABLE [dbo].[Configuration] CHECK CONSTRAINT [fk_Configuration_BusinessEntityIdentifier]
GO
ALTER TABLE [dbo].[System]  WITH CHECK ADD FOREIGN KEY([DomainIdentifier])
REFERENCES [dbo].[Domain] ([DomainIdentifier])
GO
USE [master]
GO
ALTER DATABASE [Tokenisation] SET  READ_WRITE 
GO
