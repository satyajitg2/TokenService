use [Tokenisation] 
go



CREATE INDEX SDT1_INDEX
ON [dbo].[SeedDateTime1] (SeedValue, Vault, SeedUsedFlag)

CREATE INDEX SDT2_INDEX
ON [dbo].[SeedDateTime2] (SeedValue, Vault, SeedUsedFlag)

CREATE INDEX SDT3_INDEX
ON [dbo].[SeedDateTime3] (SeedValue, Vault, SeedUsedFlag)

--SeedInteger1


CREATE INDEX SINT1_INDEX
ON [dbo].[SeedInteger1] (SeedValue, Vault, SeedUsedFlag)

--SeedInteger2
CREATE INDEX SINT2_INDEX
ON [dbo].[SeedInteger2] (SeedValue, Vault, SeedUsedFlag)

--SeedInteger3
CREATE INDEX SINT3_INDEX
ON [dbo].[SeedInteger3] (SeedValue, Vault, SeedUsedFlag)

--SeedString1

CREATE INDEX SSTR1_INDEX
ON [dbo].[SeedString1] (SeedValue, Vault, SeedUsedFlag)

--SeedString2
CREATE INDEX SSTR2_INDEX
ON [dbo].[SeedString2] (SeedValue, Vault, SeedUsedFlag)

--SeedString3
CREATE INDEX SSTR3_INDEX
ON [dbo].[SeedString3] (SeedValue, Vault, SeedUsedFlag)








/****** Script for SelectTopNRows command from SSMS  ******/

SELECT Top (1) SeedValue 
FROM [Tokenisation].[dbo].[SeedString2] 
where vault = 1 and SeedUsedFlag = 0 

SELECT COUNT(*)
  FROM [Tokenisation].[dbo].[SeedString2] where SeedUsedFlag=1

SELECT COUNT(*)
  FROM [Tokenisation].[dbo].[SeedString2] where SeedUsedFlag=0
  
  SELECT COUNT(*)
  FROM [Tokenisation].[dbo].[SeedInteger2]
  
    SELECT COUNT(*)
  FROM [Tokenisation].[dbo].[SeedDateTime2]
  
  
  Truncate table [Tokenisation].[dbo].[SeedString3]
  
  use [Tokenisation]
  go
    
SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedString3');

drop Index [SeedString1].SSTR1_INDEX

drop Index [SeedString2].SSTR2_INDEX

drop Index [SeedString3].SSTR3_INDEX

CREATE INDEX SSTR1_INDEX 
ON [dbo].[SeedString1] (SeedValue, Vault, SeedUsedFlag);

CREATE INDEX SSTR2_INDEX 
ON [dbo].[SeedString2] (SeedValue, Vault, SeedUsedFlag);

CREATE INDEX SSTR3_INDEX 
ON [dbo].[SeedString3] (SeedValue, Vault, SeedUsedFlag);


SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedString1')

SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedString2')

SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedString3')

SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedInteger1')

SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedDateTime1')

SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedDateTime2')

SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID('SeedDateTime3')


