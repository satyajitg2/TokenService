use [TOKEN_GVA_SIT] 
go

--TokenDateTime
CREATE INDEX TD_INDEX
ON [Vault].[TokenDateTime] (RepeatableFlag)

CREATE INDEX TI_INDEX
ON [Vault].[TokenInteger] (RepeatableFlag)

CREATE INDEX TS_INDEX
ON [Vault].[TokenString] (RepeatableFlag)