use [TOKEN_GVA_SIT] 
go

CREATE INDEX SDT1_INDEX
ON [TECORE].[SeedDateTime1] (SeedValue, Vault, SeedUsedFlag)

CREATE INDEX SDT2_INDEX
ON [TECORE].[SeedDateTime2] (SeedValue, Vault, SeedUsedFlag)

CREATE INDEX SDT3_INDEX
ON [TECORE].[SeedDateTime3] (SeedValue, Vault, SeedUsedFlag)

--SeedInteger1


CREATE INDEX SINT1_INDEX
ON [TECORE].[SeedInteger1] (SeedValue, Vault, SeedUsedFlag)

--SeedInteger2
CREATE INDEX SINT2_INDEX
ON [TECORE].[SeedInteger2] (SeedValue, Vault, SeedUsedFlag)

--SeedInteger3
CREATE INDEX SINT3_INDEX
ON [TECORE].[SeedInteger3] (SeedValue, Vault, SeedUsedFlag)

--SeedString1

CREATE INDEX SSTR1_INDEX
ON [TECORE].[SeedString1] (SeedValue, Vault, SeedUsedFlag)

--SeedString2
CREATE INDEX SSTR2_INDEX
ON [TECORE].[SeedString2] (SeedValue, Vault, SeedUsedFlag)

--SeedString3
CREATE INDEX SSTR3_INDEX
ON [TECORE].[SeedString3] (SeedValue, Vault, SeedUsedFlag)
