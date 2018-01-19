USE [Tokenisation]
GO
--Create new table - APIAuth
CREATE TABLE [Tokenisation].[dbo].[APIAUTH](
	[ID] [int] NOT NULL IDENTITY(1,1),
	[SYSTEMNAME] [varchar](10) NOT NULL,	
	[BUSINESSENTITY] [varchar](10) NOT NULL,
	[DOMAIN] [varchar](10) NOT NULL,
	[APINAME] [varchar](10) NOT NULL
 CONSTRAINT [pk_APIAUTHorization] PRIMARY KEY 
(
	[SYSTEMNAME],
	[BUSINESSENTITY],
	[DOMAIN],
	[APINAME]
)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--Data for new APIAUTH table.
INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', 'PBTV', 'TOKENISE');

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', 'PBTV', 'DETOKENISE');

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', 'PBTV', 'RETOKENISE');



INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','MCO', 'PBTV', 'TOKENISE');

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','MCO', 'PBTV', 'DETOKENISE');

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','MCO', 'PBTV', 'RETOKENISE');


INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'PBTV', 'TOKENISE');

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'PBTV', 'DETOKENISE');

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'PBTV', 'RETOKENISE');

--Creating a Stored PROC

DROP PROCEDURE  SP_CHECKAUTHORISATION;

CREATE PROCEDURE SP_CHECKAUTHORISATION @System varchar(10), @BusinessEntity varchar(10), 
@Domain varchar(10), @APIName varchar(10), @IsAuthorized varchar(20) OUTPUT
AS
SELECT @IsAuthorized = 
CASE  count(1)
 when 1 THEN 'AUTHORISED'
 ELSE 'NOT AUTHORISED'
 END
FROM [Tokenisation].[dbo].[APIAUTH] 
WHERE SYSTEMNAME = @System
AND BUSINESSENTITY = @BusinessEntity
AND DOMAIN = @Domain
AND APINAME = @APIName;
	
--Testing the proc as follows:

DECLARE @IsAuthorized varchar(20)
EXEC SP_CHECKAUTHORISATION @System = 'AVQ', @BusinessEntity = 'MCO', @Domain = 'PBTV', @APIName = 'DETOKENISE', @IsAuthorized = @IsAuthorized OUTPUT
Select @IsAuthorized