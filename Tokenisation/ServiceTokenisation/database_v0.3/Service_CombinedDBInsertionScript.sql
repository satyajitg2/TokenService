USE [Tokenisation]
GO


INSERT INTO [dbo].[Table_Set]
           ([TokenSet]
           ,[TokenIntegerTable]
           ,[TokenStringValueTable]
           ,[TokenDateTimeValueTable])
     VALUES
           (1,'SeedInteger1','SeedString1','SeedDateTime1')


INSERT INTO [dbo].[Table_Set]
           ([TokenSet]
           ,[TokenIntegerTable]
           ,[TokenStringValueTable]
           ,[TokenDateTimeValueTable])
     VALUES
           (2,'SeedInteger2','SeedString2','SeedDateTime2')

INSERT INTO [dbo].[Table_Set]
           ([TokenSet]
           ,[TokenIntegerTable]
           ,[TokenStringValueTable]
           ,[TokenDateTimeValueTable])
     VALUES
           (3,'SeedInteger3','SeedString3','SeedDateTime3')

INSERT INTO [dbo].[TokenDecision]
           ([Id]
           ,[TableSetToUseByTokenSeeder]
           ,[TableSetToUseByTokenEngine]
           ,[TokenToStartGenerateInteger]
           ,[TokenToStartGenerateString]
           ,[TokenToStartGenerateDateTime]
           ,[MaxTokenToGenerateInteger]
           ,[MaxTokenToGenerateString]
           ,[MaxTokenToGenerateDateTime]
           ,[ConfigurationIdentifier])
     VALUES
           (1,1,0,1000000000000000126,'WaaaaaY','3000-01-01 00:00:00',30000,30000,30000,1)
  

INSERT INTO [dbo].[TokenDecision]
           ([Id]
           ,[TableSetToUseByTokenSeeder]
           ,[TableSetToUseByTokenEngine]
           ,[TokenToStartGenerateInteger]
           ,[TokenToStartGenerateString]
           ,[TokenToStartGenerateDateTime]
           ,[MaxTokenToGenerateInteger]
           ,[MaxTokenToGenerateString]
           ,[MaxTokenToGenerateDateTime]
           ,[ConfigurationIdentifier])
     VALUES
           (2,1,0,4000000000000000050,'Waaaab6','5001-01-01 00:00:00',30000,30000,30000,2)

INSERT INTO [dbo].[TokenDecision]
           ([Id]
           ,[TableSetToUseByTokenSeeder]
           ,[TableSetToUseByTokenEngine]
           ,[TokenToStartGenerateInteger]
           ,[TokenToStartGenerateString]
           ,[TokenToStartGenerateDateTime]
           ,[MaxTokenToGenerateInteger]
           ,[MaxTokenToGenerateString]
           ,[MaxTokenToGenerateDateTime]
           ,[ConfigurationIdentifier])
     VALUES
           (3,1,0,5000000000000000060,'WaaabaY','7001-01-01 00:00:00',30000,30000,30000,3)
    
INSERT INTO [dbo].[Configuration]
           ([ConfigurationIdentifier]
           ,[StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityName])
     VALUES
           (1,'@',1000000000000000000,3000000000000000000,3000,5000,'MCO')
           

INSERT INTO [dbo].[Configuration]
           ([ConfigurationIdentifier]
           ,[StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityName])
     VALUES
           (2,'*',3000000000000000001,5000000000000000000,5001,7000,'CH')

INSERT INTO [dbo].[Configuration]
           ([ConfigurationIdentifier]
           ,[StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityName])
     VALUES
           (3,'$',5000000000000000001,9000000000000000000,7001,9998,'GVA')

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

INSERT INTO [Tokenisation].[dbo].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'DUMMY', 'RETOKENISE');

           