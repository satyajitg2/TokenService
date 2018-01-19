/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 16, 2016 
 * Target DBMS : Microsoft SQL Server 2012
 *
 * Data Insertion script 
 **** NOTE:
 **** Before Executing - Make sure that the DDL scripts are successfully executed.
 */
 
--March 7th, following tables are removed. 
--1)API
--2) APIACCESS
--3) BusinessEntity
--4) Domain
--5) TokenVault
--6) System
 
	USE [TOKEN_GVA_SIT]
	GO


/*
 * SQL Code Generation 
 * [TokenVault] will not be used
 */
	   
------Data for Table_Set table.


INSERT INTO [TOKEN_GVA_SIT].[TECORE].[Table_Set] ([TokenSet], [TokenIntegerTable], [TokenStringValueTable], [TokenDateTimeValueTable])
VALUES (1, 'SeedInteger1', 'SeedString1', 'SeedDateTime1')


INSERT INTO [TOKEN_GVA_SIT].[TECORE].[Table_Set] ([TokenSet], [TokenIntegerTable], [TokenStringValueTable], [TokenDateTimeValueTable])
VALUES (2, 'SeedInteger2', 'SeedString2', 'SeedDateTime2')

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[Table_Set] ([TokenSet], [TokenIntegerTable], [TokenStringValueTable], [TokenDateTimeValueTable])
     VALUES
           (3, 'SeedInteger3', 'SeedString3', 'SeedDateTime3')


--Data for new Configuration table.
INSERT INTO [TOKEN_GVA_DEV].[TECORE].[Configuration]
           ([StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityName])
     VALUES
           ('@',1000000000000000000,3000000000000000000,3000,5000,'MCO')
           

INSERT INTO [TOKEN_GVA_DEV].[TECORE].[Configuration]
           ([StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityName])
     VALUES
           ('*',3000000000000000001,5000000000000000000,5001,7000,'CH')

INSERT INTO [TOKEN_GVA_DEV].[TECORE].[Configuration]
           ([StringVaultIdentifer]
           ,[IntegerTokenStartValue]
           ,[IntegerTokenEndValue]
           ,[DateTimeTokenYearStart]
           ,[DateTimeTokenYearEnd]
           ,[BusinessEntityName])
     VALUES
           ('$',5000000000000000001,9000000000000000000,7001,9998,'GVA')

       
 
--Data setup for new APIAUTH table.
INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', 'PBTV', 'TOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', 'PBTV', 'DETOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', 'PBTV', 'RETOKENISE');



INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','MCO', 'PBTV', 'TOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','MCO', 'PBTV', 'DETOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','MCO', 'PBTV', 'RETOKENISE');



INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'PBTV', 'TOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'PBTV', 'DETOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('AVQ','GVA', 'PBTV', 'RETOKENISE');


--Data for new TokenDecision table.

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[TokenDecision]
           ([Id]
           ,[TableSetToUseByTokenSeeder]
           ,[TableSetToUseByTokenEngine]
           ,[TokenToStartGenerateInteger]
           ,[TokenToStartGenerateString]
           ,[TokenToStartGenerateDateTime]
           ,[MaxTokenToGenerateInteger]
           ,[MaxTokenToGenerateString]
           ,[MaxTokenToGenerateDateTime]
           ,[MinTokenInInteger]
           ,[MinTokenInString]
           ,[MinTokenInDateTime]
           ,[Token_Rotation_Freq]
           ,[Next_Rotation_time]
           ,[ConfigurationIdentifier])
     VALUES
           (1,1,0,1000000000000000126,'WaaaaaY','3000-01-01 00:00:00',30,30,30,5,5,5,1439,'2016-03-03 18:46:31.037',1)
GO

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[TokenDecision]
           ([Id]
           ,[TableSetToUseByTokenSeeder]
           ,[TableSetToUseByTokenEngine]
           ,[TokenToStartGenerateInteger]
           ,[TokenToStartGenerateString]
           ,[TokenToStartGenerateDateTime]
           ,[MaxTokenToGenerateInteger]
           ,[MaxTokenToGenerateString]
           ,[MaxTokenToGenerateDateTime]
           ,[MinTokenInInteger]
           ,[MinTokenInString]
           ,[MinTokenInDateTime]
           ,[Token_Rotation_Freq]
           ,[Next_Rotation_time]
           ,[ConfigurationIdentifier])
     VALUES
           (2,1,0,4000000000000000050,'Waaaab6','5001-01-01 00:00:00',30,30,30,5,5,5,1439,'2016-03-03 18:46:31.037',2)
GO

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[TokenDecision]
           ([Id]
           ,[TableSetToUseByTokenSeeder]
           ,[TableSetToUseByTokenEngine]
           ,[TokenToStartGenerateInteger]
           ,[TokenToStartGenerateString]
           ,[TokenToStartGenerateDateTime]
           ,[MaxTokenToGenerateInteger]
           ,[MaxTokenToGenerateString]
           ,[MaxTokenToGenerateDateTime]
           ,[MinTokenInInteger]
           ,[MinTokenInString]
           ,[MinTokenInDateTime]
           ,[Token_Rotation_Freq]
           ,[Next_Rotation_time]
           ,[ConfigurationIdentifier])
     VALUES
           (3,1,0,5000000000000000060,'WaaabaY','7001-01-01 00:00:00',30,30,30,5,5,5,1439,'2016-03-03 18:46:31.037',3)
GO

--**Insert for Report --

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (1
           ,'INFO'
           ,'Token Pattern Matching CSD Values'
           ,'STORED_PROCEDURE'
           ,'EXECUTE Vault.SP_TOKEN_PATTERN_MATCHER'
           ,'22-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (2
           ,'INFO'
           ,'GVA Vault size - String'
           ,'SQL_QUERY'
           ,'select COUNT(*) from TokenString'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);      
           
INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (3
           ,'INFO'
           ,'GVA Vault size - Integer'
           ,'SQL_QUERY'
           ,'select COUNT(*) from TokenInteger'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);          
           
 INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (4
           ,'INFO'
           ,'GVA Vault size - DateTime'
           ,'SQL_QUERY'
           ,'select COUNT(*) from TokenDateTime'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);      
           
INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (5
           ,'INFO'
           ,'MCO Vault size - String'
           ,'SQL_QUERY'
           ,'select COUNT(*) from TokenString'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);      
           
INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (6
           ,'INFO'
           ,'MCO Vault size - Integer'
           ,'SQL_QUERY'
           ,'select COUNT(*) from TokenInteger'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);          
           
 INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (7
           ,'INFO'
           ,'MCO Vault size - DateTime'
           ,'SQL_QUERY'
           ,'select COUNT(*) from TokenDateTime'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);  
  
   INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (8
           ,'INFO'
           ,'Seed table rotation status'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_SEED_TABLE_ROTATION_STATUS ?, ?'
           ,'00-00'
		   ,'1439'
		   ,''
		   ,''
           ,1);            
           
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (9
           ,'INFO'
           ,'String_Seeds_available_MCO'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_STRING_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);  
           
           
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (10
           ,'INFO'
           ,'String_Seeds_available_CH'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_STRING_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);        
           
            
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (11
           ,'INFO'
           ,'String_Seeds_available_GVA'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_STRING_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);               
           
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (12
           ,'INFO'
           ,'Number_Seeds_available_MCO'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_INTEGER_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);  
           
           
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (13
           ,'INFO'
           ,'Number_Seeds_available_CH'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_INTEGER_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);        
           
            
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (14
           ,'INFO'
           ,'Number_Seeds_available_GVA'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_INTEGER_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);             
           
            INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (15
           ,'INFO'
           ,'DateTime_Seeds_available_MCO'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_DATETIME_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);             
           
            INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (16
           ,'INFO'
           ,'DateTime_Seeds_available_CH'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_DATETIME_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);             
           
            INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (17
           ,'INFO'
           ,'DateTime_Seeds_available_GVA'
           ,'STORED_PROCEDURE'
           ,'EXEC TECORE.SP_DATETIME_SEEDS_AVAILABLE ?,?'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);  
           
           
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (18
           ,'INFO'
           ,'Token Bank in Use'
           ,'SQL_QUERY'
           ,'from TokenDecision'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);          
           
           INSERT INTO [TOKEN_GVA_SIT].[TECORE].[MonitoringJobs]
           ([M_ID]
           ,[M_Type]
           ,[M_Name]
           ,[M_Script_Type]
           ,[M_Script]
           ,[M_Start_Pattern]
		   ,[M_frequency_Inmins]
		   ,[M_LastReportTime]
		   ,[M_LastReportedValue]
           ,[Is_Enabled])
     VALUES
           (19
           ,'INFO'
           ,'Switchover Date/Time of Seed Table'
           ,'SQL_QUERY'
           ,'from TokenDecision'
           ,'00-00'
		   ,'59'
		   ,''
		   ,''
           ,1);  


USE [TOKEN_GVA_SIT]
GO

--/****** Object:  Table [TECORE].[TokenDecision]    Script Date: 04/12/2016 11:06:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO
--Added two columns to fix the On-The-Fly issue.
--Thefix has two parts - dB level and code changes.
ALTER TABLE [TECORE].[TokenDecision] ADD UpdatedDateTime datetime
GO

ALTER TABLE [TECORE].[TokenDecision] ADD UpdateCount bigint
GO

UPDATE [TECORE].[TokenDecision] SET UpdatedDateTime=GETDATE(),UpdateCount=0
GO

SET ANSI_PADDING OFF
GO


		   
		   
--End of Insertion script


