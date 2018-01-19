USE [TOKEN_GVA_SIT]
GO

/****** Object:  Table [TECORE].[TokenDecision]    Script Date: 04/12/2016 11:06:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

ALTER TABLE [TOKEN_GVA_SIT].[TECORE].[TokenDecision] ADD UpdatedDateTime datetime

ALTER TABLE [TOKEN_GVA_SIT].[TECORE].[TokenDecision] ADD UpdateCount bigint

UPDATE [TOKEN_GVA_SIT].[TECORE].[TokenDecision] SET UpdatedDateTime=GETDATE(),UpdateCount=0


GO

SET ANSI_PADDING OFF
GO
