USE [TOKEN_GVA_SIT]
GO

/****** Object:  StoredProcedure [TECORE].[SP_SWITCH_TOKENSEEDER]    Script Date: 5/2/2016 11:03:09 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [TECORE].[SP_SWITCH_TOKENSEEDER] 
AS
BEGIN
SET NOCOUNT ON
DECLARE 
@SeedInt bigint,
@SeedDate bigint,
@SeedStr bigint,

@pointerTE bigint,
@MinInt bigint,
@MinStr bigint,
@MinDate bigint,
@ConfigIdentifier bigint

DECLARE db_decision CURSOR FOR  
SELECT [MinTokenInInteger],[MinTokenInString],[MinTokenInDateTime], [ConfigurationIdentifier]
											FROM [TECORE].[TOKENDECISION]
 

SELECT @pointerTE = TableSetToUseByTokenEngine FROM [TECORE].[TOKENDECISION] WHERE configurationidentifier = 1

OPEN db_decision   
FETCH NEXT FROM db_decision INTO @MinInt, @MinDate, @MinStr, @ConfigIdentifier

WHILE @@FETCH_STATUS = 0
BEGIN
	IF @pointerTE = 1 BEGIN
	
		SELECT @SeedInt = COUNT(*)
         FROM [TECORE].[SeedInteger1] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
         
		SELECT @SeedDate = COUNT(*)
         FROM [TECORE].[SEEDDATETIME1] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
         
		SELECT @SeedStr = COUNT(*)
         FROM [TECORE].[SEEDSTRING1] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
		
		
		If (@SeedInt <= @MinInt OR @SeedDate <= @MinDate OR @SeedStr <= @MinStr) BEGIN	
		UPDATE [TECORE].[TokenDecision] 
		SET TableSetToUseByTokenSeeder = 3, 
		TableSetToUseByTokenEngine = 2

		END
	END
	IF @pointerTE = 2 BEGIN
	
		SELECT @SeedInt = COUNT(*)
         FROM [TECORE].[SeedInteger2] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
         
		SELECT @SeedDate = COUNT(*)
         FROM [TECORE].[SEEDDATETIME2] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
         
		SELECT @SeedStr = COUNT(*)
         FROM [TECORE].[SEEDSTRING2] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
		
		If (@SeedInt <= @MinInt OR @SeedDate <= @MinDate OR @SeedStr <= @MinStr) 	BEGIN
		UPDATE [TECORE].[TokenDecision] 
		SET TableSetToUseByTokenSeeder = 1, 
		TableSetToUseByTokenEngine = 3
		
		END
	END
	IF @pointerTE = 3 BEGIN
	
		SELECT @SeedInt = COUNT(*)
         FROM [TECORE].[SeedInteger3] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
         
		SELECT @SeedDate = COUNT(*)
         FROM [TECORE].[SEEDDATETIME3] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier
         
		SELECT @SeedStr = COUNT(*)
         FROM [TECORE].[SEEDSTRING3] where SeedUsedFlag='false' group by Vault having Vault=@ConfigIdentifier

		If (@SeedInt <= @MinInt OR @SeedDate <= @MinDate OR @SeedStr <= @MinStr) 	BEGIN
		UPDATE [TECORE].[TokenDecision] 
		SET TableSetToUseByTokenSeeder = 2, 
		TableSetToUseByTokenEngine = 1
		END
	END
	FETCH NEXT FROM db_decision INTO @MinInt, @MinDate, @MinStr, @ConfigIdentifier
  END
  CLOSE db_decision
  DEALLOCATE db_decision
END;
GO


