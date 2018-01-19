 USE [TOKEN_GVA_SIT]
GO
/****** Object:  StoredProcedure [vault].[SP_TOKEN_PATTERN_MATCHER]    Script Date: 03/01/2016 11:21:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<[SP_TOKEN_PATTERN_MATCHER]>
-- =============================================

IF OBJECT_ID('vault.SP_TOKEN_PATTERN_MATCHER]', 'U') IS NOT NULL 
  DROP procedure vault.SP_TOKEN_PATTERN_MATCHER]; 
  
CREATE PROCEDURE [vault].[SP_TOKEN_PATTERN_MATCHER]

AS
BEGIN

SET NOCOUNT ON;

 -- Creation of temporary table for holding Datetime Sourcefieldname

    CREATE TABLE vault.Temp1(
        SourceFieldName varchar(30), CSDValue varchar(255), BusinessEntityName varchar(255));   

--Insertion of value in temp table
    INSERT INTO vault.Temp1(SourceFieldName, CSDValue, BusinessEntityName)
	SELECT SourceFieldName, CSDValue, BusinessEntityName FROM TokenDateTime
	WHERE  CSDValue <> '' AND (CSDValue LIKE '[0-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]%')
	and CONVERT (date, TokenCreationDateTime) = CONVERT (date, GETDATE()) ;
 
--union all queries of string, integer and datetime
(select CSDValue, SourceFieldName, 'TokenString' as TableName, BusinessEntityName from TokenString
	where len(CSDValue)=10 
	and SUBSTRING(CSDValue,1,2)='^^'
	and SUBSTRING(CSDValue,3,1) in ('!','@','#','%','&','*','$','+','^')
	and CONVERT (date, TokenCreationDateTime) = CONVERT (date, GETDATE()))
	union all
	(select CSDValue, SourceFieldName, 'TokenInteger' as TableName , BusinessEntityName from TokenInteger
	where isnumeric(CSDValue) =1 and LEN(CSDValue)=19
	and convert(BIGINT,CSDValue)>=1000000000000000000
	and convert(BIGINT,CSDValue)<=9000000000000000000
	and CONVERT (date, TokenCreationDateTime) = CONVERT (date, GETDATE()))
	union all
	( 
	SELECT SourceFieldName, CSDValue,'TokenDateTime' as TableName , BusinessEntityName
	FROM vault.Temp1 WHERE YEAR(CSDValue)>=3000 AND YEAR(CSDValue)<=9998)
	
	
	
 --drop temp table
	DROP TABLE vault.Temp1;

END;

--#*************


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<SP_DATETIME_SEEDS_AVAILABLE>
-- =============================================
IF OBJECT_ID('TECORE.SP_DATETIME_SEEDS_AVAILABLE]', 'U') IS NOT NULL 
  DROP procedure TECORE.SP_DATETIME_SEEDS_AVAILABLE]; 

CREATE PROCEDURE [TECORE].[SP_DATETIME_SEEDS_AVAILABLE]
@VAULT INT, @SEEDCOUNT BIGINT OUTPUT

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

   DECLARE @TABLE int

	Select @TABLE = tableSetToUseByTokenEngine from TokenDecision ;
	
	IF(@TABLE =1)
		SELECT @SEEDCOUNT = COUNT(*) from SeedDateTime1 WHERE Vault = @VAULT and SeedUsedFlag = 0
	ELSE IF(@TABLE = 2)
		SELECT @SEEDCOUNT = COUNT(*) from SeedDateTime2 WHERE Vault = @VAULT and SeedUsedFlag = 0
	ELSE IF(@TABLE = 3)
		SELECT @SEEDCOUNT = COUNT(*) from SeedDateTime3 WHERE Vault = @VAULT and SeedUsedFlag = 0
END
GO


---------

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<SP_INTEGER_SEEDS_AVAILABLE>
-- =============================================
IF OBJECT_ID('TECORE.SP_INTEGER_SEEDS_AVAILABLE]', 'U') IS NOT NULL 
  DROP procedure TECORE.SP_INTEGER_SEEDS_AVAILABLE]; 

CREATE PROCEDURE [TECORE].[SP_INTEGER_SEEDS_AVAILABLE]
@VAULT INT, @SEEDCOUNT BIGINT OUTPUT
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

   DECLARE @TABLE int

	Select @TABLE = tableSetToUseByTokenEngine from TokenDecision ;
	
	IF(@TABLE =1)
		SELECT @SEEDCOUNT = COUNT(*) from SeedInteger1 WHERE Vault = @VAULT and SeedUsedFlag = 0
	ELSE IF(@TABLE = 2)
		SELECT @SEEDCOUNT = COUNT(*) from SeedInteger2 WHERE Vault = @VAULT and SeedUsedFlag = 0
	ELSE IF(@TABLE = 3)
		SELECT @SEEDCOUNT = COUNT(*) from SeedInteger3 WHERE Vault = @VAULT and SeedUsedFlag = 0
END
GO




-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<SP_SEED_TABLE_ROTATION_STATUS>
-- =============================================
IF OBJECT_ID('TECORE.SP_SEED_TABLE_ROTATION_STATUS]', 'U') IS NOT NULL 
  DROP procedure TECORE.SP_SEED_TABLE_ROTATION_STATUS]; 
  
CREATE PROCEDURE [TECORE].[SP_SEED_TABLE_ROTATION_STATUS]
@LAST_UPDATED_VALUE VARCHAR(500), @STATUS VARCHAR(50) OUTPUT

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	DECLARE @TABLE int, @COUNT_DATETIME INT,  @COUNT_STRING INT,  @COUNT_INTEGER INT, @NEXT_REPORT_TIME DATETIME
	
	Select @TABLE = tableSetToUseByTokenEngine from TokenDecision ;
	
	SELECT @NEXT_REPORT_TIME = Next_Rotation_time FROM TokenDecision
	
	IF(SYSDATETIME() < @NEXT_REPORT_TIME)
		IF(@TABLE <> @LAST_UPDATED_VALUE)
			SET @STATUS = 'Emergency Rollover'

	
	
	IF(@TABLE =1)
	BEGIN
	SELECT @COUNT_DATETIME = COUNT(*) from SeedDateTime1 WHERE SeedUsedFlag = 0
	SELECT @COUNT_STRING = COUNT(*) from SeedString1 WHERE SeedUsedFlag = 0
	SELECT @COUNT_INTEGER = COUNT(*) from SeedInteger1 WHERE SeedUsedFlag = 0
	
	IF(@COUNT_DATETIME =0 OR @COUNT_INTEGER = 0 OR @COUNT_STRING = 0)
		SET @STATUS = 'On the Fly Generation'
	ELSE
		SET @STATUS = 'Normal'	
					
	END
	
	ELSE IF(@TABLE = 2)
	BEGIN
	SELECT @COUNT_DATETIME = COUNT(*) from SeedDateTime2 WHERE SeedUsedFlag = 0
	SELECT @COUNT_STRING = COUNT(*) from SeedString2 WHERE SeedUsedFlag = 0
	SELECT @COUNT_INTEGER = COUNT(*) from SeedInteger2 WHERE SeedUsedFlag = 0
	
	IF(@COUNT_DATETIME =0 OR @COUNT_INTEGER = 0 OR @COUNT_STRING = 0)
		SET @STATUS = 'On the Fly Generation'
	ELSE
		SET @STATUS = 'Normal'	
					
	END
	
	ELSE IF(@TABLE = 3)
	BEGIN
	SELECT @COUNT_DATETIME = COUNT(*) from SeedDateTime3 WHERE SeedUsedFlag = 0
	SELECT @COUNT_STRING = COUNT(*) from SeedString3 WHERE SeedUsedFlag = 0
	SELECT @COUNT_INTEGER = COUNT(*) from SeedInteger3 WHERE SeedUsedFlag = 0
	
	IF(@COUNT_DATETIME =0 OR @COUNT_INTEGER = 0 OR @COUNT_STRING = 0)
		SET @STATUS = 'On the Fly Generation'
	ELSE
		SET @STATUS = 'Normal'	
					
	END
    
    
END
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<SP_STRING_SEEDS_AVAILABLE>
-- =============================================
IF OBJECT_ID('TECORE.SP_STRING_SEEDS_AVAILABLE]', 'U') IS NOT NULL 
  DROP procedure TECORE.SP_STRING_SEEDS_AVAILABLE]; 

CREATE PROCEDURE [TECORE].[SP_STRING_SEEDS_AVAILABLE]
@VAULT INT, @SEEDCOUNT BIGINT OUTPUT	
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	DECLARE @TABLE int

	Select @TABLE = tableSetToUseByTokenEngine from TokenDecision ;
	
	IF(@TABLE =1)
		SELECT @SEEDCOUNT = COUNT(*) from SeedString1 WHERE Vault = @VAULT and SeedUsedFlag = 0
	ELSE IF(@TABLE = 2)
		SELECT @SEEDCOUNT = COUNT(*) from SeedString2 WHERE Vault = @VAULT and SeedUsedFlag = 0
	ELSE IF(@TABLE = 3)
		SELECT @SEEDCOUNT = COUNT(*) from SeedString3 WHERE Vault = @VAULT and SeedUsedFlag = 0
	
	
END
