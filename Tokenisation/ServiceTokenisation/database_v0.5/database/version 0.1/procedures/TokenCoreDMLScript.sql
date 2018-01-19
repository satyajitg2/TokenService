/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
  *
 * Tokenisation DML script. Define Procedure and Function.
 * 
 */

USE [TOKEN_GVA_SIT]
GO

/***********  Partitioning function *************/
/***********  The Partitioning is being done by Month *************/


CREATE PARTITION FUNCTION [PartitioningByMonth] (datetime)
AS RANGE RIGHT FOR VALUES ('20160201 23:59:59.997', '20160301 23:59:59.997', '20160401 23:59:59.997',
'20160501 23:59:59.997', '20160601 23:59:59.997', '20160701 23:59:59.997', '20160801 23:59:59.997', 
'20160901 23:59:59.997', '20161001 23:59:59.997', '20161101 23:59:59.997', '20161201 23:59:59.997');

CREATE PARTITION SCHEME PartitionBymonth
AS PARTITION PartitioningBymonth
TO (January, February, March, 
April, May, June, July, 
August, September, October, 
November, December,[PRIMARY]);

--'PRIMARY' is marked as the next used filegroup in partition scheme if not available 


/***************  Creating a TECORE.SP_CHECKAUTHORISATION Procedure ***************/

IF OBJECT_ID('TECORE.SP_CHECKAUTHORISATION', 'U') IS NOT NULL 
  DROP PROCEDURE TECORE.SP_CHECKAUTHORISATION; 

USE [TOKEN_GVA_SIT]
GO
CREATE PROCEDURE SP_CHECKAUTHORISATION @System varchar(100), @BusinessEntity varchar(10), 
@Domain varchar(10), @APIName varchar(10), @IsAuthorized varchar(20) OUTPUT
AS
SELECT @IsAuthorized = 
CASE  count(1)
 when 1 THEN 'AUTHORISED'
 ELSE 'NOT AUTHORISED'
 END	
FROM [TOKEN_GVA_SIT].[TECORE].[APIAUTH] 
WHERE upper(SYSTEMNAME) = @System
AND upper(BUSINESSENTITY) = @BusinessEntity
AND upper(DOMAIN) = @Domain
AND upper(APINAME) = @APIName;

/***************  Creating a TECORE.SP_SWITCH_TOKENSEEDER Procedure ***************/

IF OBJECT_ID('TECORE.SP_SWITCH_TOKENSEEDER', 'U') IS NOT NULL 
  DROP procedure TECORE.SP_SWITCH_TOKENSEEDER; 

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