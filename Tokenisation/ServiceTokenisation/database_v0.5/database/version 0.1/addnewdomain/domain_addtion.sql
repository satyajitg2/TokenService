/*
 * SQL Code Generation
 * Project :      Tokenization (v1.0)
 *
 * Date Created : Tuesday, Februrary 19, 2016 
 * Target DBMS : Microsoft SQL Server 2012
 *
 * Data Insertion script 
 To Create new DOMAIN entry, need to create entries in
 3 tables in TECORE schema - Tables - Domain, APIAccess, APIAuth
 
 * Replace <NEW_DOMAIN> with the Domain Name you want to specify
 */

Use TOKEN_GVA_SIT
GO


/* [APIAUTH] table is a STATIC data table. No dependency with any other table in the Schema */

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', '<NEW_DOMAIN>', 'TOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', '<NEW_DOMAIN>', 'DETOKENISE');

INSERT INTO [TOKEN_GVA_SIT].[TECORE].[APIAUTH] ([SystemName], [BusinessEntity], [Domain], [APIName]) 
VALUES ('CDI','CH', '<NEW_DOMAIN>', 'RETOKENISE');