USE [Tokenisation]
GO

INSERT INTO [dbo].[API]
           ([APIIdentifier]
           ,[APIName])
     VALUES
           (1
           ,'Tokenisation')


INSERT INTO [dbo].[API]
           ([APIIdentifier]
           ,[APIName])
     VALUES
           (2
           ,'DeTokenisation')

INSERT INTO [dbo].[API]
           ([APIIdentifier]
           ,[APIName])
     VALUES
           (3
           ,'ReTokenisation')


INSERT INTO [dbo].[Domain]
           ([DomainIdentifier]
           ,[DomainName])
     VALUES
           (1
           ,'PBTV')

INSERT INTO [dbo].[Domain]
           ([DomainIdentifier]
           ,[DomainName])
     VALUES
           (2
           ,'ACTIMISE')


INSERT INTO [dbo].[System]
           ([SystemIdentifier]
           ,[SystemName]
           ,[DomainIdentifier])
     VALUES
           (1
           ,'AVQ'
           ,1)


INSERT INTO [dbo].[System]
           ([SystemIdentifier]
           ,[SystemName]
           ,[DomainIdentifier])
     VALUES
           (2
           ,'CDI'
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (1
           ,1
           ,1
           ,1
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (2
           ,1
           ,1
           ,2
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (3
           ,1
           ,1
           ,1
           ,2)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (4
           ,1
           ,1
           ,2
           ,2)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (5
           ,2
           ,1
           ,1
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (6
           ,2
           ,1
           ,2
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (7
           ,2
           ,1
           ,1
           ,2)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (8
           ,2
           ,1
           ,2
           ,2)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (9
           ,3
           ,1
           ,1
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (10
           ,3
           ,1
           ,2
           ,1)

INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (11
           ,3
           ,1
           ,1
           ,2)


INSERT INTO [dbo].[APIAccess]
           ([APIAccessIdentifier]
           ,[APIIdentifier]
           ,[SystemIdentifier]
           ,[BusinessEntityIdentifier]
           ,[DomainIdentifier])
     VALUES
           (12
           ,3
           ,1
           ,2
           ,2)



INSERT INTO [dbo].[TokenVault]
           ([TokenVaultIdentifier]
           ,[TokenVaultName]
           ,[ConnectionURL]
           ,[ConnectionUserName]
           ,[ConnectionPassword])
     VALUES
           (1
           ,'MCO'
           ,'jdbc:sqlserver://10.118.14.51:1433;databaseName=TOK_MCO'
           ,'adminuser'
           ,'admin@123')

INSERT INTO [dbo].[TokenVault]
           ([TokenVaultIdentifier]
           ,[TokenVaultName]
           ,[ConnectionURL]
           ,[ConnectionUserName]
           ,[ConnectionPassword])
     VALUES
           (2
           ,'TOK_VAULT'
           ,'jdbc:sqlserver://10.118.14.105:1433;databaseName=TOK_VAULT'
           ,'adminuser'
           ,'mounty@15')

INSERT INTO [dbo].[BusinessEntity]
           ([BusinessEntityIdentifier]
           ,[TokenVaultIdentifier]
           ,[BusinessEntityName]
           ,[APIAccessIdentifier])
     VALUES
           (1
           ,1
           ,'MCO'
           ,1)


INSERT INTO [dbo].[BusinessEntity]
           ([BusinessEntityIdentifier]
           ,[TokenVaultIdentifier]
           ,[BusinessEntityName]
           ,[APIAccessIdentifier])
     VALUES
           (2
           ,2
           ,'CH'
           ,1)


INSERT INTO [dbo].[BusinessEntity]
           ([BusinessEntityIdentifier]
           ,[TokenVaultIdentifier]
           ,[BusinessEntityName]
           ,[APIAccessIdentifier])
     VALUES
           (3
           ,2
           ,'GVA'
           ,1)


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
           (1,1,0,1000000000000000126,'WaaaaaY','3000-01-01 00:00:00',30,30,30,1)
  

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
           (2,1,0,4000000000000000050,'Waaaab6','5001-01-01 00:00:00',30,30,30,2)

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
           (3,1,0,5000000000000000060,'WaaabaY','7001-01-01 00:00:00',30,30,30,3)
    
INSERT INTO [dbo].[Configuration]
           ([ConfigurationIdentifier]
           ,[StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityIdentifier])
     VALUES
           (1,'!,@,#',1000000000000000000,3000000000000000000,3000,5000,1)
           

INSERT INTO [dbo].[Configuration]
           ([ConfigurationIdentifier]
           ,[StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityIdentifier])
     VALUES
           (2,'%,&,*',3000000000000000001,5000000000000000000,5001,7000,2)

INSERT INTO [dbo].[Configuration]
           ([ConfigurationIdentifier]
           ,[StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityIdentifier])
     VALUES
           (3,'$,+',5000000000000000001,9000000000000000000,7001,9998,3)
           