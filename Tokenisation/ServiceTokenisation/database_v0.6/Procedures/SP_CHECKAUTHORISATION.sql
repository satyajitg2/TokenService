USE [TOKEN_GVA_SIT]
GO

/****** Object:  StoredProcedure [TECORE].[SP_CHECKAUTHORISATION]    Script Date: 5/2/2016 11:02:48 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



CREATE PROCEDURE [TECORE].[SP_CHECKAUTHORISATION] @System varchar(100), @BusinessEntity varchar(10), 
@Domain varchar(10), @APIName varchar(10), @IsAuthorized varchar(20) OUTPUT
AS
SELECT @IsAuthorized = 
CASE  count(1)
 when 1 THEN 'AUTHORISED'
 ELSE 'NOT AUTHORISED'
 END	
FROM [TOKEN_GVA_SIT].[TECORE].[APIAUTH] 
WHERE upper(SYSTEMNAME) = upper(@System)
AND upper(BUSINESSENTITY) = upper(@BusinessEntity)
AND upper(DOMAIN) = upper(@Domain)
AND upper(APINAME) = upper(@APIName);


GO


